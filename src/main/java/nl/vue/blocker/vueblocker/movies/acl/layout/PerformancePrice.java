package nl.vue.blocker.vueblocker.movies.acl.layout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "performance_id",
        "price_oid",
        "price_name",
        "price",
        "reservation_fee",
        "ticket_fee",
        "allowed_for",
        "minimum_quantity",
        "maximum_quantity",
        "ticket_type_oid",
        "seating_area_oid"
})
public class PerformancePrice {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("performance_id")
    public Integer performanceId;
    @JsonProperty("price_oid")
    public String priceOid;
    @JsonProperty("price_name")
    public String priceName;
    @JsonProperty("price")
    public Integer price;
    @JsonProperty("reservation_fee")
    public String reservationFee;
    @JsonProperty("ticket_fee")
    public String ticketFee;
    @JsonProperty("allowed_for")
    public Integer allowedFor;
    @JsonProperty("minimum_quantity")
    public Integer minimumQuantity;
    @JsonProperty("maximum_quantity")
    public Integer maximumQuantity;
    @JsonProperty("ticket_type_oid")
    public Object ticketTypeOid;
    @JsonProperty("seating_area_oid")
    public Integer seatingAreaOid;

}
