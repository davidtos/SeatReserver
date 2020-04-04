package nl.vue.blocker.vueblocker.movies.acl.layout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fee",
        "has3dGlasses",
        "hasRental3dGlasses",
        "name",
        "price"
})
public class Glasses {

    @JsonProperty("fee")
    public Integer fee;
    @JsonProperty("has3dGlasses")
    public Boolean has3dGlasses;
    @JsonProperty("hasRental3dGlasses")
    public Boolean hasRental3dGlasses;
    @JsonProperty("name")
    public String name;
    @JsonProperty("price")
    public Float price;

}
