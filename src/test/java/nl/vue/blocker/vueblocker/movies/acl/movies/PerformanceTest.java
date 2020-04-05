package nl.vue.blocker.vueblocker.movies.acl.movies;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PerformanceTest {


    @Test
    void mapToDomain(){
        Performance performance = createPerformance();

        nl.vue.blocker.vueblocker.movies.domain.Performance domain = performance.toDomain();

        assertThat(createDomainPerformance()).usingRecursiveComparison().isEqualTo(domain);
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