package nl.vue.blocker.vueblocker.reservations.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.reservations.domain.FutureReservations;
import nl.vue.blocker.vueblocker.reservations.persistance.FutureReservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

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
