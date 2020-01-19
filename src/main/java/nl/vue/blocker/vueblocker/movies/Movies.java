package nl.vue.blocker.vueblocker.movies;

import lombok.AllArgsConstructor;
import nl.vue.blocker.vueblocker.acl.VueApi;
import nl.vue.blocker.vueblocker.acl.movies.Movie;
import nl.vue.blocker.vueblocker.acl.movies.Performance;
import nl.vue.blocker.vueblocker.acl.vueconnector.Location;
import nl.vue.blocker.vueblocker.reservations.FutureReservationsRepo;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class Movies {

    final private VueApi vueApi;
    final private FutureReservationsRepo futureReservationsRepo;

    Mono<Movie[]> getExpectedMovies() {
        return vueApi.getExpectedMovies(LocalDate.now(), 730);
    }

    public List<Movie> getMoviesByTitle(String search) {
        List<Movie> futureAndComingMovies = getFutureAndComingMovies();
        return futureAndComingMovies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> getFutureAndComingMovies() {
        Movie[] expectedMovies = vueApi.getExpectedMovies(LocalDate.now(), 730).block();
        Movie[] performancesByCinema = vueApi.getPerformancesByCinema(Location.EINDHOVEN, LocalDate.now());
        List<Movie> uniqueMovies = new ArrayList<>(Arrays.asList(expectedMovies));

        for (Movie movie : performancesByCinema) {
            if (uniqueMovies.stream().noneMatch(movie1 -> movie.getSlug().equals(movie1.getSlug()))){
                uniqueMovies.add(movie);
            }
        }
        return uniqueMovies;
    }

    public Mono<Performance[]> getPerformanceForMovie(int movieId) {
       return vueApi.getPerformanceForMovie(movieId, Location.EINDHOVEN);
    }
}
