package nl.vue.blocker.vueblocker.acl.reserve;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "data",
        "message"
})
public class Reservation {

    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("data")
    public Data data;
    @JsonProperty("message")
    public Object message;
    public String sessionId;

}
