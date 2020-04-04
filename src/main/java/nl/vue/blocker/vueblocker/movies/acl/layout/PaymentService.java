package nl.vue.blocker.vueblocker.movies.acl.layout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "id",
        "collapsed",
        "version"
})
public class PaymentService {

    @JsonProperty("name")
    public String name;
    @JsonProperty("id")
    public String id;
    @JsonProperty("collapsed")
    public Boolean collapsed;
    @JsonProperty("version")
    public Integer version;

}
