package nl.vue.blocker.vueblocker.movies.acl.reserve;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "price",
        "performanceId",
        "seat",
        "priceCategory"
})
public class Reservation_ {

    @JsonProperty("id")
    public String id;
    @JsonProperty("price")
    public Object price;
    @JsonProperty("performanceId")
    public Integer performanceId;
    @JsonProperty("seat")
    public Seat seat;
    @JsonProperty("priceCategory")
    public PriceCategory priceCategory;

}
