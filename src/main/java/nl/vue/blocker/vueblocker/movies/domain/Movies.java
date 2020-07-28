package nl.vue.blocker.vueblocker.movies.domain;

import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.List;

public interface Movies {
    Flux<Movie> getExpectedMovies();

    List<Movie> getMoviesByTitle(String search);

    Collection<Movie> getFutureAndComingMovies();

    Flux<Performance> getPerformanceByMovieId(int movieId);
}
