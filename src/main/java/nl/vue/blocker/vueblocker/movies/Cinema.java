package nl.vue.blocker.vueblocker.movies;

import nl.vue.blocker.vueblocker.acl.layout.PerformanceLayout;
import nl.vue.blocker.vueblocker.acl.movies.Performance;
import nl.vue.blocker.vueblocker.acl.reserve.Reservation;
import nl.vue.blocker.vueblocker.acl.vueconnector.Location;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface Cinema {
    // per day
    Movie[] getPerformancesByLocationAndDate(Location location, LocalDate localDate);

    // get later
    Movie[] getFuturePerformancesByLocationAndDate(Location location);

    // now in Vue
    Movie[] getPerformancesByCinema(Location location, LocalDate localDate);

    Mono<Movie[]> getExpectedMovies(LocalDate localDate, int range);

    PerformanceLayout getPerformanceLayout(String title, String performanceId);

    Mono<Performance[]> getPerformanceForMovie(int movieId, Location location);

    Reservation reserveSeat(int performanceId, int seatId);
}
