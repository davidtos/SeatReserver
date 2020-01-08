package nl.vue.blocker.vueblocker.acl.layout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "oid",
        "name",
        "slug",
        "latitude",
        "longitude",
        "street",
        "doornumber",
        "postalcode",
        "city",
        "email",
        "phonenumber",
        "accessibility",
        "comments",
        "business_hours_text",
        "og_title",
        "og_description",
        "og_image",
        "has_technical_failure",
        "allow_movie_pass_creation",
        "distance",
        "business_hours",
        "rates",
        "facilities",
        "directions",
        "carousel",
        "auditoriums"
})
public class Cinema {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("oid")
    public String oid;
    @JsonProperty("name")
    public String name;
    @JsonProperty("slug")
    public String slug;
    @JsonProperty("latitude")
    public String latitude;
    @JsonProperty("longitude")
    public String longitude;
    @JsonProperty("street")
    public String street;
    @JsonProperty("doornumber")
    public String doornumber;
    @JsonProperty("postalcode")
    public String postalcode;
    @JsonProperty("city")
    public String city;
    @JsonProperty("email")
    public String email;
    @JsonProperty("phonenumber")
    public String phonenumber;
    @JsonProperty("accessibility")
    public String accessibility;
    @JsonProperty("comments")
    public String comments;
    @JsonProperty("business_hours_text")
    public String businessHoursText;
    @JsonProperty("og_title")
    public String ogTitle;
    @JsonProperty("og_description")
    public String ogDescription;
    @JsonProperty("og_image")
    public String ogImage;
    @JsonProperty("has_technical_failure")
    public Integer hasTechnicalFailure;
    @JsonProperty("allow_movie_pass_creation")
    public Integer allowMoviePassCreation;
    @JsonProperty("distance")
    public Object distance;
    @JsonProperty("business_hours")
    public Object businessHours;
    @JsonProperty("rates")
    public Object rates;
    @JsonProperty("facilities")
    public Object facilities;
    @JsonProperty("directions")
    public Object directions;
    @JsonProperty("carousel")
    public Object carousel;
    @JsonProperty("auditoriums")
    public Object auditoriums;

}
