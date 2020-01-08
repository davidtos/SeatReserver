package nl.vue.blocker.vueblocker.reservations;

import org.springframework.data.repository.CrudRepository;

public interface FutureReservationsRepo extends CrudRepository<FutureReservation,Long> {
}
