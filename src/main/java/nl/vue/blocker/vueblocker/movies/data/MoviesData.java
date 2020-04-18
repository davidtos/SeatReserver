package nl.vue.blocker.vueblocker.movies.data;

import lombok.AllArgsConstructor;
import nl.vue.blocker.vueblocker.movies.domain.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MoviesData implements Movies {

    final private RedisTemplate<String, List<Movie>> redisTemplate;
    final private Cinema cinema;

    @Override
    public Mono<Movie[]> getExpectedMovies() {
        return cinema.getExpectedMovies(LocalDate.now(), 730);
    }

    @Override
    public List<Movie> getMoviesByTitle(String search) {
        if (redisTemplate.hasKey(search)) {
            return redisTemplate.opsForValue().get(search);
        } else {
            List<Movie> movieList = getFutureAndComingMovies().stream()
                    .filter(movie -> movie.getTitle().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
            redisTemplate.opsForValue().set(search, movieList, Duration.ofMinutes(10));
            return movieList;
        }
    }

    @Override
    public Set<Movie> getFutureAndComingMovies() {
        Set<Movie> uniqueMovies = new HashSet<>();

        uniqueMovies.addAll(Arrays.asList(cinema.getExpectedMovies(LocalDate.now(), 730).block()));
        uniqueMovies.addAll(Arrays.asList(cinema.getPerformancesByCinema(Location.EINDHOVEN, LocalDate.now())));
        uniqueMovies.addAll(Arrays.asList(cinema.getFuturePerformancesByLocationAndDate(Location.EINDHOVEN)));

        return uniqueMovies;
    }

    @Override
    public Mono<Performance[]> getPerformanceByMovieId(int movieId) {
        return cinema.getPerformanceForMovie(movieId, Location.EINDHOVEN);
    }
}
