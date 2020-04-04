package nl.vue.blocker.vueblocker.movies.acl.layout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Value",
        "Description",
        "GroupName"
})
public class IdealBank {

    @JsonProperty("Value")
    public String value;
    @JsonProperty("Description")
    public String description;
    @JsonProperty("GroupName")
    public String groupName;

}