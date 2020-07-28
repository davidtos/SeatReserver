package nl.vue.blocker.vueblocker.reservations.domain;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.movies.domain.MovieFoundEvent;
import nl.vue.blocker.vueblocker.reservations.persistance.FutureReservation;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MovieBroker {

    private final FutureReservations futureReservations;
    private final SeatReserver seatReserver;

    @Async
    @EventListener
    public void handleContextStart(MovieFoundEvent event) {
        Iterable<FutureReservation> reservation = futureReservations.findAllByMovieTitle(event.getMovie().getTitle());
        if (event.getMovie().getPerformances() == null && reservation.iterator().hasNext()) {
            log.info(String.format("Failed to create reservation job because the movie %s has no performance(s)", event.getMovie().getTitle()));
        } else {
            reservation.forEach(futureReservation ->
                    event.getMovie().getPerformances().forEach(performance ->
                            seatReserver.reserveSeat(event.getMovie(), futureReservation, performance)));
        }
    }
}
