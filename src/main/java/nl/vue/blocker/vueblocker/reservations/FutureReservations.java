package nl.vue.blocker.vueblocker.reservations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FutureReservations {

    private final FutureReservationsRepo futureReservationsRepo;

    FutureReservation save(FutureReservation futureReservation){
        return futureReservationsRepo.save(futureReservation);
    }

    Iterable<FutureReservation> findAll(){
        return futureReservationsRepo.findAll();
    }

    public Optional<FutureReservation> findById(Long id) {
        return futureReservationsRepo.findById(id);
    }
}
