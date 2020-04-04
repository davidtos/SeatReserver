package nl.vue.blocker.vueblocker.movies.acl.reserve;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "data",
        "message"
})
public class ReservationUnsuccessful {

    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("data")
    public int data;
    @JsonProperty("message")
    public String message;

}
