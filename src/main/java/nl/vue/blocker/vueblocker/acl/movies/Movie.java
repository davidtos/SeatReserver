package nl.vue.blocker.vueblocker.acl.movies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.vue.blocker.vueblocker.acl.layout.GeneralMessage;
import nl.vue.blocker.vueblocker.acl.layout.KijkwijzerArray;

import java.util.List;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("oid")
    private String oid;
    @JsonProperty("edi")
    private String edi;
    @JsonProperty("title")
    private String title;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("description")
    private String description;
    @JsonProperty("genres")
    private String genres;
    @JsonProperty("kijkwijzer")
    private String kijkwijzer;
    @JsonProperty("cast")
    private String cast;
    @JsonProperty("director")
    private String director;
    @JsonProperty("vue_url")
    private String vueUrl;
    @JsonProperty("trailer_url_low")
    private String trailerUrlLow;
    @JsonProperty("trailer_url_high")
    private String trailerUrlHigh;
    @JsonProperty("trailer_youtube_id")
    private String trailerYoutubeId;
    @JsonProperty("image")
    private String image;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("special_category")
    private String specialCategory;
    @JsonProperty("playingtime")
    private Integer playingtime;
    @JsonProperty("is_edited")
    private Integer isEdited;
    @JsonProperty("edit_date")
    private String editDate;
    @JsonProperty("vue_created")
    private Integer vueCreated;
    @JsonProperty("og_title")
    private String ogTitle;
    @JsonProperty("og_description")
    private String ogDescription;
    @JsonProperty("og_image")
    private String ogImage;
    @JsonProperty("variant_grouping")
    private Integer variantGrouping;
    @JsonProperty("image_relative")
    private String imageRelative;
    @JsonProperty("image_missing")
    private Object imageMissing;
    @JsonProperty("full_title")
    private Object fullTitle;
    @JsonProperty("rating_average")
    private Object ratingAverage;
    @JsonProperty("rating_count")
    private Object ratingCount;
    @JsonProperty("stills")
    private Object stills;
    @JsonProperty("performances")
    private List<Performance> performances = null;
    @JsonProperty("all_performances")
    private Object allPerformances;
    @JsonProperty("variants")
    private Object variants;
    @JsonProperty("kijkwijzerArray")
    public List<KijkwijzerArray> kijkwijzerArray = null;
    @JsonProperty("generalMessages")
    public List<GeneralMessage> generalMessages = null;

}
