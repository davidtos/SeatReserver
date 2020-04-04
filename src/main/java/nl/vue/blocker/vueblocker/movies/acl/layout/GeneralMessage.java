package nl.vue.blocker.vueblocker.movies.acl.layout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "type",
        "name",
        "content",
        "display",
        "medium",
        "page_slug",
        "cinema_id",
        "auditorium_id",
        "movie_id",
        "performance_id",
        "target_audience_id",
        "datalayer_event",
        "promo_code_campaign",
        "placement",
        "sequence",
        "active_start",
        "active_end"
})
public class GeneralMessage {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("type")
    public String type;
    @JsonProperty("name")
    public String name;
    @JsonProperty("content")
    public String content;
    @JsonProperty("display")
    public String display;
    @JsonProperty("medium")
    public String medium;
    @JsonProperty("page_slug")
    public Object pageSlug;
    @JsonProperty("cinema_id")
    public String cinemaId;
    @JsonProperty("auditorium_id")
    public String auditoriumId;
    @JsonProperty("movie_id")
    public Object movieId;
    @JsonProperty("performance_id")
    public Object performanceId;
    @JsonProperty("target_audience_id")
    public Object targetAudienceId;
    @JsonProperty("datalayer_event")
    public Object datalayerEvent;
    @JsonProperty("promo_code_campaign")
    public Object promoCodeCampaign;
    @JsonProperty("placement")
    public String placement;
    @JsonProperty("sequence")
    public Integer sequence;
    @JsonProperty("active_start")
    public Object activeStart;
    @JsonProperty("active_end")
    public Object activeEnd;

}
