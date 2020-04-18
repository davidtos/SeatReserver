package nl.vue.blocker.vueblocker.movies.acl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.movies.domain.Movie;
import nl.vue.blocker.vueblocker.movies.domain.MovieWithPerformanceEvent;
import nl.vue.blocker.vueblocker.movies.domain.Movies;
import nl.vue.blocker.vueblocker.movies.domain.Performance;
import nl.vue.blocker.vueblocker.reservations.FutureReservation;
import nl.vue.blocker.vueblocker.reservations.FutureReservations;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class MovieAvailableCheckerJob implements Job {

    private final Movies movies;
    private final FutureReservations futureReservations;
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * @param context parameters for this job
     */
    public void execute(JobExecutionContext context) {
        Iterable<FutureReservation> futureReservations = this.futureReservations.findAll();
        List<MovieReservationData> moviesMatchingFutureReservation = getReservedMoviesThatAreAlsoAvailable(futureReservations);

        moviesMatchingFutureReservation.forEach(movieReservationData -> {
            Performance[] performanceForMovie = movies.getPerformanceByMovieId(movieReservationData.getMovie().getId())
                    .block();
            if (performanceForMovie != null) {
                for (Performance performance : performanceForMovie) {
                    MovieWithPerformanceEvent movieWithPerformanceEvent = new MovieWithPerformanceEvent(this, movieReservationData.movie, movieReservationData.futureReservation, performance);
                    applicationEventPublisher.publishEvent(movieWithPerformanceEvent);
                }
            }
        });
    }

    private List<MovieReservationData> getReservedMoviesThatAreAlsoAvailable(Iterable<FutureReservation> futureReservations) {
        List<MovieReservationData> movieReservationData = new ArrayList<>();
        for (FutureReservation futureReservation : futureReservations) {
            Optional<Movie> optionalMovie = this.movies.getFutureAndComingMovies().stream()
                    .filter(movie -> movieTitleContainsFutureReservationTitle(movie, futureReservation))
                    .findFirst();

            optionalMovie.ifPresent(movie -> movieReservationData
                    .add(new MovieReservationData(movie, futureReservation)));
        }
        return movieReservationData;
    }

    private boolean movieTitleContainsFutureReservationTitle(Movie movie, FutureReservation futureReservation) {
        return movie
                .getTitle()
                .toLowerCase()
                .contains(futureReservation.getTitle().toLowerCase());
    }

    @AllArgsConstructor
    @Getter
    static
    class MovieReservationData {
        private final Movie movie;
        private final FutureReservation futureReservation;
    }
}