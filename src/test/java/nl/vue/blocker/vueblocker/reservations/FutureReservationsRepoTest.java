package nl.vue.blocker.vueblocker.reservations;

import nl.vue.blocker.vueblocker.reservations.persistance.FutureReservation;
import nl.vue.blocker.vueblocker.reservations.persistance.FutureReservationsRepo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FutureReservationsRepoTest {

    @Autowired
    private FutureReservationsRepo futureReservationsRepo;

    private static Stream<Arguments> partialMovieTitles() {
        return Stream.of(
                Arguments.of("Wonder", "Wonder Woman"),
                Arguments.of("Woman", "Wonder Woman"),
                Arguments.of("Birds of Prey", "Birds of Prey: And the Fantabulous Emancipation of One Harley Quinn"),
                Arguments.of("Alpha", "The Alpha Test"),
                Arguments.of("Wonder Woman", "Wonder Woman"),
                Arguments.of("WoNdEr WoMaN", "Wonder Woman")
        );
    }

    @ParameterizedTest
    @MethodSource("partialMovieTitles")
    public void findByTitleIsIn(String reservedTitle, String cinemaTitle){
        FutureReservation futureReservation = FutureReservation.builder().title(reservedTitle).build();
        futureReservationsRepo.save(futureReservation);
        Iterable<FutureReservation> result = futureReservationsRepo.findAllReservationsbyMovieTitle(cinemaTitle);

        assertThat(futureReservation).isEqualTo(result.iterator().next());
    }
}