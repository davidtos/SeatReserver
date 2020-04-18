package nl.vue.blocker.vueblocker.movies.domain;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface Movies {
    Mono<Movie[]> getExpectedMovies();

    List<Movie> getMoviesByTitle(String search);

    Set<Movie> getFutureAndComingMovies();

    Mono<Performance[]> getPerformanceByMovieId(int movieId);
}
