package nl.vue.blocker.vueblocker.reservations.domain;

import lombok.AllArgsConstructor;
import nl.vue.blocker.vueblocker.reservations.persistance.FutureReservation;
import nl.vue.blocker.vueblocker.reservations.persistance.FutureReservationsRepo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FutureReservations {

    private final FutureReservationsRepo futureReservationsRepo;

    public FutureReservation save(FutureReservation futureReservation) {
        return futureReservationsRepo.save(futureReservation);
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
