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
        Movie movie = createMovie();
        nl.vue.blocker.vueblocker.movies.domain.Movie domainMovie = movie.toDomain();
        assertThat(createDomainMovie()).usingRecursiveComparison().isEqualTo(domainMovie);
    }

    private Movie createMovie() {
        return Movie.builder()
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
                .performances(Collections.singletonList(createPerformance()))
                .kijkwijzerArray(Collections.singletonList(new KijkwijzerArray()))
                .generalMessages(Collections.singletonList(new GeneralMessage()))
                .build();
    }

    private Performance createPerformance() {
        return Performance.builder()
                .id(1)
                .oid("oid")
                .cinemaId(1)
                .movieId(1)
                .auditoriumId(1)
                .has2d(1)
                .has3d(1)
                .hasDbox(1)
                .hasXd(1)
                .hasAtmos(1)
                .hasDolbycinema(1)
                .hasHfr("hasHfr")
                .hasOv(1)
                .hasNl(1)
                .start("start")
                .end("end")
                .hasBreak(1)
                .visible(1)
                .disabled(1)
                .isEdited(1)
                .occupiedSeats(1)
                .totalSeats(1)
                .price("price")
                .fullPrice("fullPrice")
                .reservationFee("reservationFee")
                .ticketFee("ticketFee")
                .hasRental3dGlasses("hasRental3dGlasses")
                .cinema("cinema")
                .cinemaName("cinemaName")
                .auditoriumName("auditoriumName")
                .specialCategory("specialCategory")
                .variantName("variantName")
                .variantSlug("variantSlug")
                .prices("prices")
                .startTimeInSeconds(1)
                .build();
    }

    private nl.vue.blocker.vueblocker.movies.domain.Movie createDomainMovie() {
        return nl.vue.blocker.vueblocker.movies.domain.Movie.builder()
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
                .performances(Collections.singletonList(createDomainPerformance()))
                .kijkwijzerArray(Collections.singletonList(new KijkwijzerArray()))
                .generalMessages(Collections.singletonList(new GeneralMessage()))
                .build();
    }

    private nl.vue.blocker.vueblocker.movies.domain.Performance createDomainPerformance() {
        return nl.vue.blocker.vueblocker.movies.domain.Performance.builder()
                .id(1)
                .oid("oid")
                .cinemaId(1)
                .movieId(1)
                .auditoriumId(1)
                .has2d(true)
                .has3d(true)
                .hasDbox(true)
                .hasXd(true)
                .hasAtmos(true)
                .hasDolbycinema(true)
                .hasHfr("hasHfr")
                .hasOv(true)
                .hasNl(true)
                .start("start")
                .end("end")
                .hasBreak(true)
                .visible(true)
                .disabled(true)
                .isEdited(true)
                .occupiedSeats(1)
                .totalSeats(1)
                .price("price")
                .fullPrice("fullPrice")
                .reservationFee("reservationFee")
                .ticketFee("ticketFee")
                .hasRental3dGlasses("hasRental3dGlasses")
                .cinema("cinema")
                .cinemaName("cinemaName")
                .auditoriumName("auditoriumName")
                .specialCategory("specialCategory")
                .variantName("variantName")
                .variantSlug("variantSlug")
                .prices("prices")
                .startTimeInSeconds(1)
                .build();
    }
}