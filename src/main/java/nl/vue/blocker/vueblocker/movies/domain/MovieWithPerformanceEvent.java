package nl.vue.blocker.vueblocker.movies.domain;

import lombok.Getter;
import lombok.Setter;
import nl.vue.blocker.vueblocker.reservations.FutureReservation;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class MovieWithPerformanceEvent extends ApplicationEvent {

    private Movie movie;
    private FutureReservation futureReservation;
    private Performance performance;

    public MovieWithPerformanceEvent(Object source, Movie movie, FutureReservation futureReservation, Performance performance) {
        super(source);
        this.movie = movie;
        this.futureReservation = futureReservation;
        this.performance = performance;
    }
}
