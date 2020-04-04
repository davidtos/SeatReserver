package nl.vue.blocker.vueblocker.reservations;

import nl.vue.blocker.vueblocker.movies.domain.Movies;
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


}