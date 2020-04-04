package nl.vue.blocker.vueblocker.movies.acl.layout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "restriction",
        "slug",
        "title"
})
public class KijkwijzerArray {

    @JsonProperty("restriction")
    public Boolean restriction;
    @JsonProperty("slug")
    public String slug;
    @JsonProperty("title")
    public String title;

}
