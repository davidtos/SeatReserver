package nl.vue.blocker.vueblocker.reservations.domain;

import nl.vue.blocker.vueblocker.movies.domain.Movie;
import nl.vue.blocker.vueblocker.movies.domain.Performance;
import nl.vue.blocker.vueblocker.reservations.persistance.FutureReservation;

public interface SeatReserver {

    void reserveSeat(Movie movie, FutureReservation futureReservation, Performance performance);
}
