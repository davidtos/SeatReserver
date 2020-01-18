package nl.vue.blocker.vueblocker.acl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.acl.layout.CinemaLayoutParser;
import nl.vue.blocker.vueblocker.acl.layout.PerformanceLayout;
import nl.vue.blocker.vueblocker.acl.movies.Movie;
import nl.vue.blocker.vueblocker.acl.movies.Performance;
import nl.vue.blocker.vueblocker.acl.reserve.Reservation;
import nl.vue.blocker.vueblocker.acl.reserve.ReservationUnsuccessful;
import nl.vue.blocker.vueblocker.acl.vueconnector.Location;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Slf4j
@AllArgsConstructor
@Component
public class VueApi {

    WebClient webClient;

    // per day
    public Movie[] getPerformancesByLocationAndDate(Location location, LocalDate localDate) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movies.json")
                        .queryParam("type", "NOW_PLAYING_WITH_PERFORMANCES")
                        .queryParam("dateOffset", localDate.toString())
                        .queryParam("range", "1")
                        .queryParam("filters[cinema_id][]", "in")
                        .queryParam("filters[cinema_id][]", location.getId())
                        .build())
                .retrieve()
                .bodyToMono(Movie[].class)
                .block();
    }

    // now in Vue
    public Movie[] getPerformancesByCinema(Location location, LocalDate localDate) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movies.json")
                        .queryParam("type", "NOW_PLAYING_PREFERED_CINEMAS")
                        .queryParam("dateOffset", localDate.toString())
                        .queryParam("range", "7")
                        .queryParam("filters[cinema_id][]", "in")
                        .queryParam("filters[cinema_id][]", location.getId())
                        .build())
                .retrieve()
                .bodyToMono(Movie[].class)
                .block();
    }

    public Mono<Movie[]> getExpectedMovies(LocalDate localDate, int range) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movies.json")
                        .queryParam("type", "EXPECTED")
                        .queryParam("filters", "")
                        .queryParam("dateOffset", localDate)
                        .queryParam("range", range)
                        .build())
                .retrieve()
                .bodyToMono(Movie[].class);
    }

    public PerformanceLayout getCinemaLayout(String title, String performanceId) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/kopen/" + title + "/" + performanceId)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(CinemaLayoutParser::parseLayout)
                .block();
    }


    public Mono<Performance[]> getPerformanceForMovie(int movieId, Location location) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/performances.json")
                        .queryParam("movie_id", movieId)
                        .queryParam("cinema_ids[]", location.getId())
                        .queryParam("dateOffset", LocalDate.now())
                        .queryParam("range",365)
                        .build())
                .retrieve()
                .bodyToMono(Performance[].class);
    }

    public Reservation reserveSeat(int performanceId, int seatId) {
        ClientResponse response = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("services/api/cart.php")
                        .queryParam("action", "reserve")
                        .queryParam("performance_id", performanceId)
                        .queryParam("reservation[performanceId]", performanceId)
                        .queryParam("reservation[seat][number]", seatId)
                        .queryParam("reservation[seat][row]", "10")
                        .queryParam("reservation[seat][seat]", "1")
                        .queryParam("reservation[seat][area]", "1")
                        .queryParam("reservation[seat][type]", "1")
                        .queryParam("reservation[priceCategory][id]", "1358041131")
                        .queryParam("reservation[priceCategory][performance_id]", performanceId)
                        .queryParam("reservation[priceCategory][price_oid]", "10000000999YBADPUC")
                        .queryParam("reservation[priceCategory][price_name]", "Standaard")
                        .queryParam("reservation[priceCategory][price]", "11.1")
                        .queryParam("reservation[priceCategory][reservation_fee]", "1.00")
                        .queryParam("reservation[priceCategory][ticket_fee]", "0.00")
                        .queryParam("reservation[priceCategory][allowed_for]", "0")
                        .queryParam("reservation[priceCategory][minimum_quantity]", "1")
                        .queryParam("reservation[priceCategory][maximum_quantity]", "0")
                        .queryParam("reservation[priceCategory][ticket_type_oid]", "")
                        .queryParam("reservation[priceCategory][seating_area_oid]", "1")
                        .queryParam("reservation[priceCategory][_price]", "€+11.10")
                        .queryParam("reservation[priceCategory][type]", "REGULAR")
                        .build())
                .exchange()
                .block();

        String reservationBody = response.bodyToMono(String.class).block();

        ObjectMapper mapper = new ObjectMapper();
        try {
            Reservation reservation = mapper.readValue(reservationBody, Reservation.class);
            String sessionId = response.cookies().get("VUE").get(0).getValue();
            reservation.sessionId = sessionId;
            return reservation;

        } catch (JsonProcessingException e) {
            try {
                ReservationUnsuccessful reservationUnsuccessful = mapper.readValue(reservationBody, ReservationUnsuccessful.class);
                Reservation reservation = new Reservation();
                reservation.success = reservationUnsuccessful.success;
                return reservation;
            } catch (JsonProcessingException z) {
                log.error(z.getMessage());
                return null;
            }
        }
    }
}


