package nl.vue.blocker.vueblocker.movies.acl.layout;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "oid",
        "cinema_id",
        "name",
        "number",
        "seats",
        "has_wheelchair_seats",
        "has_xd",
        "has_dolbycinema",
        "has_hfr",
        "has_dbox",
        "has_vip",
        "has_deluxe",
        "has_rental_3d_glasses",
        "default_seating_area_oid",
        "default_seating_area_display",
        "orphan_seat_allow_empty_sides",
        "orphan_seat_min_seats_in_row",
        "seating_plan",
        "seatingPlanScale"
})
public class Auditorium {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("oid")
    public String oid;
    @JsonProperty("cinema_id")
    public Integer cinemaId;
    @JsonProperty("name")
    public String name;
    @JsonProperty("number")
    public Integer number;
    @JsonProperty("seats")
    public Integer seats;
    @JsonProperty("has_wheelchair_seats")
    public Integer hasWheelchairSeats;
    @JsonProperty("has_xd")
    public Integer hasXd;
    @JsonProperty("has_dolbycinema")
    public Integer hasDolbycinema;
    @JsonProperty("has_hfr")
    public Integer hasHfr;
    @JsonProperty("has_dbox")
    public Integer hasDbox;
    @JsonProperty("has_vip")
    public Integer hasVip;
    @JsonProperty("has_deluxe")
    public Integer hasDeluxe;
    @JsonProperty("has_rental_3d_glasses")
    public Integer hasRental3dGlasses;
    @JsonProperty("default_seating_area_oid")
    public String defaultSeatingAreaOid;
    @JsonProperty("default_seating_area_display")
    public Integer defaultSeatingAreaDisplay;
    @JsonProperty("orphan_seat_allow_empty_sides")
    public Integer orphanSeatAllowEmptySides;
    @JsonProperty("orphan_seat_min_seats_in_row")
    public Integer orphanSeatMinSeatsInRow;
    @JsonProperty("seating_plan")
    public List<SeatingPlan> seatingPlan = null;
    @JsonProperty("seatingPlanScale")
    public Integer seatingPlanScale;

}
