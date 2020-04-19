package nl.vue.blocker.vueblocker.movies.domain;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Set;

public interface Movies {
    Flux<Movie> getExpectedMovies();

    List<Movie> getMoviesByTitle(String search);

    Set<Movie> getFutureAndComingMovies();

    Flux<Performance> getPerformanceByMovieId(int movieId);
}
