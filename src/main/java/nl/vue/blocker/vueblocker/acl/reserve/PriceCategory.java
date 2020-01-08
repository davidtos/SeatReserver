package nl.vue.blocker.vueblocker.acl.reserve;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "type",
        "name",
        "discount",
        "seating_area_oid",
        "price",
        "price_oid",
        "isVerified"
})
public class PriceCategory {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("type")
    public String type;
    @JsonProperty("name")
    public Object name;
    @JsonProperty("discount")
    public Object discount;
    @JsonProperty("seating_area_oid")
    public Integer seatingAreaOid;
    @JsonProperty("price")
    public Float price;
    @JsonProperty("price_oid")
    public String priceOid;
    @JsonProperty("isVerified")
    public Object isVerified;

}
