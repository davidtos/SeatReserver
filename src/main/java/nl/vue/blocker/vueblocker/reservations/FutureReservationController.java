package nl.vue.blocker.vueblocker.reservations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.acl.movies.Movie;
import nl.vue.blocker.vueblocker.acl.movies.Performance;
import nl.vue.blocker.vueblocker.movies.Movies;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reserve")
@AllArgsConstructor
public class FutureReservationController {

    private final FutureReservations futureReservations;

    @GetMapping
    ResponseEntity<Iterable<FutureReservation>> getCollection() {
        return ResponseEntity.ok(futureReservations.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<FutureReservation> getById(@PathVariable Long id) {
        return this.futureReservations.findById(id).map(ResponseEntity::ok).orElse(null);
    }

    @PostMapping
    ResponseEntity<FutureReservation> reserveSeat(@RequestBody FutureReservation futureReservation){
        log.info(futureReservation.toString());
        FutureReservation reservation = futureReservations.save(futureReservation);

        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
                .buildAndExpand(reservation.getId()).toUri();
        return ResponseEntity.created(uri).body(reservation);
    }

}
