package nl.vue.blocker.vueblocker.movies.domain;

import lombok.AllArgsConstructor;
import nl.vue.blocker.vueblocker.movies.acl.movies.Performance;
import nl.vue.blocker.vueblocker.movies.acl.vueconnector.Location;
import nl.vue.blocker.vueblocker.reservations.FutureReservationsRepo;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class Movies {

    final private Cinema cinema;
    final private FutureReservationsRepo futureReservationsRepo;

    public Mono<Movie[]> getExpectedMovies() {
        return cinema.getExpectedMovies(LocalDate.now(), 730);
    }

    public List<Movie> getMoviesByTitle(String search) {
        List<Movie> futureAndComingMovies = getFutureAndComingMovies();
        return futureAndComingMovies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> getFutureAndComingMovies() {
        Movie[] expectedMovies = cinema.getExpectedMovies(LocalDate.now(), 730).block();
        Movie[] performancesByCinema = cinema.getPerformancesByCinema(Location.EINDHOVEN, LocalDate.now());
        Movie[] futurePerformances = cinema.getFuturePerformancesByLocationAndDate(Location.EINDHOVEN);

        Movie[] movies = Stream.concat(Arrays.stream(expectedMovies), Arrays.stream(performancesByCinema)).toArray(Movie[]::new);
        movies = Stream.concat(Arrays.stream(movies), Arrays.stream(futurePerformances)).toArray(Movie[]::new);

        List<Movie> uniqueMovies = new ArrayList<>();

        for (Movie movie : movies) {
            if (uniqueMovies.stream().noneMatch(movie1 -> movie.getSlug().equals(movie1.getSlug()))){
                uniqueMovies.add(movie);
            }
        }
        return uniqueMovies;
    }

    public Mono<Performance[]> getPerformanceForMovie(int movieId) {
       return cinema.getPerformanceForMovie(movieId, Location.EINDHOVEN);
    }
}
