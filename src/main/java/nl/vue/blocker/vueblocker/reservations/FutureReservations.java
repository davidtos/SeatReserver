package nl.vue.blocker.vueblocker.reservations;

import lombok.AllArgsConstructor;
import nl.vue.blocker.vueblocker.movies.domain.MovieWithPerformanceEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FutureReservations {

    private final FutureReservationsRepo futureReservationsRepo;
    private final SeatReserver seatReserver;

    public FutureReservation save(FutureReservation futureReservation) {
        return futureReservationsRepo.save(futureReservation);
    }

    @Async
    @EventListener
    public void handleContextStart(MovieWithPerformanceEvent event) {
        seatReserver.reserveSeat(event.getMovie(), event.getFutureReservation(), event.getPerformance());
    }

    public Iterable<FutureReservation> findAllByMovieTitle(String title){
        return futureReservationsRepo.findAllReservationsbyMovieTitle(title);
    }

    public Iterable<FutureReservation> findAll() {
        return futureReservationsRepo.findAll();
    }

    public Optional<FutureReservation> findById(Long id) {
        return futureReservationsRepo.findById(id);
    }
}
