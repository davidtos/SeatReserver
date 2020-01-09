package nl.vue.blocker.vueblocker.reservations;

import nl.vue.blocker.vueblocker.movies.Movies;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.Scheduler;

@ExtendWith(MockitoExtension.class)
public class MovieAvailableCheckerJobTest {


    @Mock
    FutureReservations futureReservations;
    @Mock
    Movies movies;
    @Mock
    Scheduler scheduler;

    @InjectMocks
    MovieAvailableCheckerJob movieAvailableCheckerJob;

    @Before
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void checkIfJobIsCreatedWhenMatchingMovieFound(){
//        given(futureReservations.findAll()).willReturn(Collections.singletonList(FutureReservation.builder().id(1L).title("Wonder woman").build()));
//        given(movies.getFutureAndComingMovies()).willReturn(Collections.singletonList(Movie.builder().id(1).title("WONDER WOMAN 1984").build()));
//        given(movies.getPerformanceForMovie(1)).willReturn(Mono.justOrEmpty(Arrays.array(Performance.builder().id(1).build())));
//
//        movieAvailableCheckerJob.execute(null);
//    }

}