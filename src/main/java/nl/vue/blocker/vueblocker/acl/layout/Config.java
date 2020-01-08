package nl.vue.blocker.vueblocker.acl.layout;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import nl.vue.blocker.vueblocker.acl.movies.Movie;
import nl.vue.blocker.vueblocker.acl.movies.Performance;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "maxTickets",
        "compeso",
        "glasses",
        "idealBanks",
        "paymentServices",
        "performancePrices",
        "movie",
        "performance",
        "releaseTypes",
        "cinema",
        "special",
        "auditorium",
        "specialConditions",
        "redirectUrl"
})
public class Config {

    @JsonProperty("maxTickets")
    public Integer maxTickets;
    @JsonProperty("compeso")
    public Compeso compeso;
    @JsonProperty("glasses")
    public Glasses glasses;
    @JsonProperty("idealBanks")
    public List<IdealBank> idealBanks = null;
    @JsonProperty("paymentServices")
    public List<PaymentService> paymentServices = null;
    @JsonProperty("performancePrices")
    public List<PerformancePrice> performancePrices = null;
    @JsonProperty("movie")
    public Movie movie;
    @JsonProperty("performance")
    public Performance performance;
    @JsonProperty("releaseTypes")
    public String releaseTypes;
    @JsonProperty("cinema")
    public Cinema cinema;
    @JsonProperty("special")
    public Object special;
    @JsonProperty("auditorium")
    public Auditorium auditorium;
    @JsonProperty("specialConditions")
    public List<Object> specialConditions = null;
    @JsonProperty("redirectUrl")
    public String redirectUrl;

}
