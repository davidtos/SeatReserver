package nl.vue.blocker.vueblocker.reservations.domain;

import lombok.AllArgsConstructor;
import nl.vue.blocker.vueblocker.movies.domain.MovieFoundEvent;
import nl.vue.blocker.vueblocker.reservations.persistance.FutureReservation;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieBroker {

    private final FutureReservations futureReservations;
    private final SeatReserver seatReserver;

    @Async
    @EventListener
    public void handleContextStart(MovieFoundEvent event) {
        Iterable<FutureReservation> reservations = futureReservations.findAllByMovieTitle(event.getMovie().getTitle());

        reservations.forEach(futureReservation ->
                event.getMovie().getPerformances().forEach(performance ->
                        seatReserver.reserveSeat(event.getMovie(), futureReservation, performance)));
    }
}
