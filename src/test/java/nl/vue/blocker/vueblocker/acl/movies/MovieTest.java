package nl.vue.blocker.vueblocker.acl.movies;

import nl.vue.blocker.vueblocker.movies.acl.layout.GeneralMessage;
import nl.vue.blocker.vueblocker.movies.acl.layout.KijkwijzerArray;
import nl.vue.blocker.vueblocker.movies.acl.movies.Movie;
import nl.vue.blocker.vueblocker.movies.acl.movies.Performance;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class MovieTest {

    @Test
    void MapToDomain() {
        Movie movie = Movie.builder()
                .id(1)
                .oid("oid")
                .edi("edi")
                .title("title")
                .slug("slug")
                .description("description")
                .genres("genres")
                .kijkwijzer("kijkwijzer")
                .cast("cast")
                .director("director")
                .vueUrl("vueUrl")
                .trailerUrlLow("trailerUrlLow")
                .trailerUrlHigh("trailerUrlHigh")
                .trailerYoutubeId("trailerYoutubeId")
                .image("image")
                .releaseDate("releaseDate")
                .specialCategory("specialCategory")
                .playingtime(1)
                .isEdited(1)
                .editDate("editDate")
                .vueCreated(1)
                .ogTitle("ogTitle")
                .ogDescription("ogDescription")
                .ogImage("ogImage")
                .variantGrouping(1)
                .imageRelative("imageRelative")
                .imageMissing("imageMissing")
                .fullTitle("fullTitle")
                .ratingAverage("ratingAverage")
                .ratingCount("ratingCount")
                .stills("stills")
                .allPerformances("allPerformances")
                .variants("variants")
                .performances(Collections.singletonList(new Performance()))
                .kijkwijzerArray(Collections.singletonList(new KijkwijzerArray()))
                .generalMessages(Collections.singletonList(new GeneralMessage()))
                .build();

        nl.vue.blocker.vueblocker.movies.domain.Movie domainMovie = movie.toDomain();

        assertThat(movie).usingRecursiveComparison().isEqualTo(domainMovie);

    }
}