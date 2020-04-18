package nl.vue.blocker.vueblocker.reservations;

import nl.vue.blocker.vueblocker.movies.domain.Movie;
import nl.vue.blocker.vueblocker.movies.domain.Performance;

public interface SeatReserver {

    void reserveSeat(Movie movie, FutureReservation futureReservation, Performance performance);
}
