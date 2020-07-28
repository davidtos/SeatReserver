package nl.vue.blocker.vueblocker.movies.data;

import nl.vue.blocker.vueblocker.movies.domain.Cinema;
import nl.vue.blocker.vueblocker.movies.domain.Movie;
import nl.vue.blocker.vueblocker.movies.domain.Performance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoviesDataTest {

    @Mock
    Cinema cinema;

    @InjectMocks
    MoviesData moviesData;

    @Test
    void getFutureAndComingMovies() {
        // given
        Movie[] moviesWithoutPerformance = {Movie.builder().slug("slug").build()};
        Movie[] moviesWithPerformance = {Movie.builder().slug("slug").performances(Collections.singletonList(Performance.builder().build())).build()};

        Flux<Movie> fluxMoviesWithoutPerformance = Flux.fromArray(moviesWithoutPerformance);
        Flux<Movie> fluxMoviesWithPerformance = Flux.fromArray(moviesWithPerformance);

        when(cinema.getExpectedMovies(any(), anyInt())).thenReturn(fluxMoviesWithoutPerformance);
        when(cinema.getPerformancesByCinema(any(), any())).thenReturn(fluxMoviesWithoutPerformance);
        when(cinema.getFuturePerformancesByLocationAndDate(any())).thenReturn(fluxMoviesWithPerformance);

        //when
        Collection<Movie> futureAndComingMovies = moviesData.getFutureAndComingMovies();

        //then
        assertThat(futureAndComingMovies.size()).isEqualTo(1);
        assertThat(futureAndComingMovies.stream().findFirst().get().getPerformances()).isNotEmpty();
    }

}