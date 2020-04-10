package nl.vue.blocker.vueblocker.movies.acl.reserve;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "reservations",
        "expiresInSeconds",
        "expirationTimestamp"
})
public class Data {

    @JsonProperty("reservations")
    public List<Reservation_> reservations = null;
    @JsonProperty("expiresInSeconds")
    public Integer expiresInSeconds;
    @JsonProperty("expirationTimestamp")
    public Integer expirationTimestamp;
}
