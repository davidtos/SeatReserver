package nl.vue.blocker.vueblocker.movies.acl.layout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "number",
        "left",
        "top",
        "angle",
        "row",
        "seat",
        "type",
        "area",
        "size",
        "status",
        "graphic",
        "id"
})
public class SeatingPlan {

    @JsonProperty("number")
    public Integer number;
    @JsonProperty("left")
    public Integer left;
    @JsonProperty("top")
    public Float top;
    @JsonProperty("angle")
    public Integer angle;
    @JsonProperty("row")
    public Integer row;
    @JsonProperty("seat")
    public Integer seat;
    @JsonProperty("type")
    public Integer type;
    @JsonProperty("area")
    public Integer area;
    @JsonProperty("size")
    public Float size;
    @JsonProperty("status")
    public Integer status;
    @JsonProperty("graphic")
    public Integer graphic;
    @JsonProperty("id")
    public Object id;

}
