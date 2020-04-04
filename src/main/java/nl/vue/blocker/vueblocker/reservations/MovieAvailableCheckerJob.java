package nl.vue.blocker.vueblocker.reservations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.acl.movies.Performance;
import nl.vue.blocker.vueblocker.movies.Movie;
import nl.vue.blocker.vueblocker.movies.Movies;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class MovieAvailableCheckerJob implements Job {
    private final FutureReservations futureReservations;
    private final Movies movies;
    private final Scheduler scheduler;

    /**
     * @param context parameters for this job
     */
    public void execute(JobExecutionContext context) {
        Iterable<FutureReservation> futureReservations = this.futureReservations.findAll();
        List<MovieReservationData> moviesMatchingFutureReservation = getReservedMoviesThatAreAlsoAvailable(futureReservations);

        moviesMatchingFutureReservation.forEach(movieReservationData -> {
            Performance[] performanceForMovie = movies.getPerformanceForMovie(movieReservationData.getMovie().getId())
                    .block();
            if (performanceForMovie != null) {
                for (Performance performance : performanceForMovie) {
                    CreateReserveJob(movieReservationData, performance);
                }
            }
        });
    }

    private List<MovieReservationData> getReservedMoviesThatAreAlsoAvailable(Iterable<FutureReservation> futureReservations) {
        List<Movie> futureAndComingMovies = this.movies.getFutureAndComingMovies();

        List<MovieReservationData> movieReservationData = new ArrayList<>();
        for (FutureReservation futureReservation : futureReservations) {
            Optional<Movie> optionalMovie = futureAndComingMovies.stream()
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

    private void CreateReserveJob(MovieReservationData movieReservationData, Performance performance) {
        try {
            JobDetail job = newJob(movieReservationData, performance.getId(), "Reserve a performance");
            SimpleTrigger trigger = trigger(job);
            scheduler.scheduleJob(job, trigger);
        } catch (ObjectAlreadyExistsException x) {
            log.error(String.format("Creating a job for a movie and performance that already exists. %s - %s",
                    movieReservationData.getMovie().getTitle(), performance.getId()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private JobDetail newJob(MovieReservationData movieReservationData, int performanceId, String description) {
        Movie movie = movieReservationData.getMovie();
        FutureReservation futureReservation = movieReservationData.getFutureReservation();
        String identity = String.format("%s - %s", movie.getTitle(), performanceId);

        return JobBuilder.newJob().ofType(SeatReservatorJob.class).storeDurably()
                .withIdentity(JobKey.jobKey(identity))
                .usingJobData("movieId", movie.getId())
                .usingJobData("performanceId", performanceId)
                .usingJobData("slugTitle",movie.getSlug())
                .usingJobData("seatRow",futureReservation.getColumn())
                .usingJobData("seatColumn",futureReservation.getColumn())
                .withDescription(description)
                .build();
    }

    private SimpleTrigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup())
                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(5))
                .startNow()
                .build();
    }

    @AllArgsConstructor
    @Getter
    static
    class MovieReservationData{
        private Movie movie;
        private FutureReservation futureReservation;
    }
}