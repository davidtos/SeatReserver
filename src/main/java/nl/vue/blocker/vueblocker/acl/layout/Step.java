package nl.vue.blocker.vueblocker.acl.layout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "action",
        "name",
        "active",
        "enabled"
})
public class Step {

    @JsonProperty("id")
    public String id;
    @JsonProperty("action")
    public String action;
    @JsonProperty("name")
    public String name;
    @JsonProperty("active")
    public Boolean active;
    @JsonProperty("enabled")
    public Boolean enabled;

}
