package nl.vue.blocker.vueblocker.reservations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.acl.movies.Movie;
import nl.vue.blocker.vueblocker.acl.movies.Performance;
import nl.vue.blocker.vueblocker.movies.Movies;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        List<Movie> moviesMatchingFutureReservation = getReservedMoviesThatAreAlsoAvailable();

        moviesMatchingFutureReservation.forEach(movie -> {
            Performance[] performanceForMovie = movies.getPerformanceForMovie(movie.getId()).block();
            if (performanceForMovie == null) {
                return;
            }
            for (Performance performance : performanceForMovie) {
                CreateReserveJob(movie, performance);
            }
        });
    }

    private List<Movie> getReservedMoviesThatAreAlsoAvailable() {
        Iterable<FutureReservation> futureReservations = this.futureReservations.findAll();
        List<Movie> futureAndComingMovies = this.movies.getFutureAndComingMovies();

        Predicate<Movie> containsMoviesThatNeedToBeReserved = movie -> {
            AtomicBoolean existInList = new AtomicBoolean(false);
            futureReservations.forEach(futureReservation -> existInList.set(movie
                    .getTitle()
                    .toLowerCase()
                    .contains(futureReservation.getTitle().toLowerCase())));
            return existInList.get();
        };

        return futureAndComingMovies.stream().filter(containsMoviesThatNeedToBeReserved).collect(Collectors.toList());
    }

    private void CreateReserveJob(Movie movie, Performance performance) {
        JobDetail job = newJob(String.format("%s - %s", movie.getTitle(), performance.getId()), movie.getId(), performance.getId(), "Reservering a performance");
        try {
            scheduler.scheduleJob(job, trigger(job));
        } catch (ObjectAlreadyExistsException x) {
            log.error(String.format("Creating a job for a movie and performance that already exists. %s - %s", movie.getTitle(), performance.getId()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private JobDetail newJob(String identity, int movieId, int performanceId, String description) {
        return JobBuilder.newJob().ofType(SeatReservatorJob.class).storeDurably()
                .withIdentity(JobKey.jobKey(identity))
                .usingJobData("movieId", movieId)
                .usingJobData("performanceId", performanceId)
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
}