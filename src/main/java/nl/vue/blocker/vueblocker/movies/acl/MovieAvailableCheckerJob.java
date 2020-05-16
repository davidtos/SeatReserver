package nl.vue.blocker.vueblocker.movies.acl;

import lombok.AllArgsConstructor;
import nl.vue.blocker.vueblocker.movies.domain.MovieFoundEvent;
import nl.vue.blocker.vueblocker.movies.domain.Movies;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieAvailableCheckerJob implements Job {

    private final Movies movies;
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * @param context parameters for this job
     */
    public void execute(JobExecutionContext context) {

        this.movies.getFutureAndComingMovies().stream()
                .map(movie -> new MovieFoundEvent(this, movie))
                .forEach(applicationEventPublisher::publishEvent);
    }
}