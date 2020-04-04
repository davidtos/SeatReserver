package nl.vue.blocker.vueblocker.movies.acl.layout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pin_length",
        "transaction_fee",
        "online_package_3d_glasses_costs",
        "online_package_donation_costs"
})
public class Compeso {

    @JsonProperty("pin_length")
    public Integer pinLength;
    @JsonProperty("transaction_fee")
    public Float transactionFee;
    @JsonProperty("online_package_3d_glasses_costs")
    public Float onlinePackage3dGlassesCosts;
    @JsonProperty("online_package_donation_costs")
    public Integer onlinePackageDonationCosts;

}
