package nl.vue.blocker.vueblocker.acl.reserve;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "number",
        "row",
        "seat",
        "area",
        "type"
})
public class Seat {

    @JsonProperty("number")
    public Integer number;
    @JsonProperty("row")
    public Integer row;
    @JsonProperty("seat")
    public Integer seat;
    @JsonProperty("area")
    public Integer area;
    @JsonProperty("type")
    public Integer type;

}
