package nl.vue.blocker.vueblocker.movies.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.movies.domain.Movie;
import nl.vue.blocker.vueblocker.movies.domain.Movies;
import nl.vue.blocker.vueblocker.movies.domain.Performance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final Movies movies;

    @GetMapping
    public Flux<Movie> getMoviesData() {
        return movies.getExpectedMovies();
    }

    @GetMapping("/search/{search}")
    public List<Movie> searchForMovie(@PathVariable String search){
        return movies.getMoviesByTitle(search);
    }

    @GetMapping("/performances/{id}")
    public Flux<Performance> getPerformanceForMovie(@PathVariable int id){
        return movies.getPerformanceByMovieId(id);
    }
}
