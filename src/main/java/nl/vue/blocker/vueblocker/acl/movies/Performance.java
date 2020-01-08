package nl.vue.blocker.vueblocker.acl.movies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Performance {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("oid")
    private String oid;
    @JsonProperty("cinema_id")
    private Integer cinemaId;
    @JsonProperty("movie_id")
    private Integer movieId;
    @JsonProperty("auditorium_id")
    private Integer auditoriumId;
    @JsonProperty("has_2d")
    private Integer has2d;
    @JsonProperty("has_3d")
    private Integer has3d;
    @JsonProperty("has_dbox")
    private Integer hasDbox;
    @JsonProperty("has_xd")
    private Integer hasXd;
    @JsonProperty("has_atmos")
    private Integer hasAtmos;
    @JsonProperty("has_dolbycinema")
    private Integer hasDolbycinema;
    @JsonProperty("has_hfr")
    private Object hasHfr;
    @JsonProperty("has_ov")
    private Integer hasOv;
    @JsonProperty("has_nl")
    private Integer hasNl;
    @JsonProperty("start")
    private String start;
    @JsonProperty("end")
    private String end;
    @JsonProperty("has_break")
    private Integer hasBreak;
    @JsonProperty("visible")
    private Integer visible;
    @JsonProperty("disabled")
    private Integer disabled;
    @JsonProperty("is_edited")
    private Integer isEdited;
    @JsonProperty("occupied_seats")
    private Integer occupiedSeats;
    @JsonProperty("total_seats")
    private Integer totalSeats;
    @JsonProperty("price")
    private Object price;
    @JsonProperty("full_price")
    private Object fullPrice;
    @JsonProperty("reservation_fee")
    private Object reservationFee;
    @JsonProperty("ticket_fee")
    private Object ticketFee;
    @JsonProperty("has_rental_3d_glasses")
    private Object hasRental3dGlasses;
    @JsonProperty("cinema")
    private Object cinema;
    @JsonProperty("cinema_name")
    private Object cinemaName;
    @JsonProperty("auditorium_name")
    private Object auditoriumName;
    @JsonProperty("special_category")
    private Object specialCategory;
    @JsonProperty("variant_name")
    private Object variantName;
    @JsonProperty("variant_slug")
    private Object variantSlug;
    @JsonProperty("prices")
    private Object prices;
    @JsonProperty("startTimeInSeconds")
    public Integer startTimeInSeconds;

}