package nl.vue.blocker.vueblocker.reservations;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FutureReservationsRepo extends CrudRepository<FutureReservation,Long> {

    @Query("SELECT u FROM FutureReservation u WHERE UPPER(?1) like CONCAT('%', UPPER(u.title),'%')")
    Iterable<FutureReservation> findAllReservationsbyMovieTitle(String title);
}
