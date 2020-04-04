package nl.vue.blocker.vueblocker.movies.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.movies.domain.Movies;
import nl.vue.blocker.vueblocker.movies.acl.movies.Performance;
import nl.vue.blocker.vueblocker.movies.domain.Movie;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final Movies movies;

    @GetMapping
    public Mono<Movie[]> getMovies(){
        return movies.getExpectedMovies();
    }

    @GetMapping("/search/{search}")
    public List<Movie> searchForMovie(@PathVariable String search){
        return movies.getMoviesByTitle(search);
    }

    @GetMapping("/performances/{id}")
    public Mono<Performance[]> getPerformanceForMovie(@PathVariable int id){
        return movies.getPerformanceForMovie(id);
    }
}
