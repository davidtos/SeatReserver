package nl.vue.blocker.vueblocker.acl.layout;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "performanceId",
        "config",
        "occupiedSeats",
        "expiresInSeconds",
        "reservations",
        "isManualGlassesSelection",
        "glasses",
        "isRestrictionsConfirmed",
        "hasDonation",
        "steps",
        "specialPromotion"
})
public class PerformanceLayout {

    @JsonProperty("performanceId")
    public Integer performanceId;
    @JsonProperty("config")
    public Config config;
    @JsonProperty("occupiedSeats")
    public List<Integer> occupiedSeats = null;
    @JsonProperty("expiresInSeconds")
    public Integer expiresInSeconds;
    @JsonProperty("reservations")
    public List<Object> reservations = null;
    @JsonProperty("isManualGlassesSelection")
    public Boolean isManualGlassesSelection;
    @JsonProperty("glasses")
    public Integer glasses;
    @JsonProperty("isRestrictionsConfirmed")
    public Boolean isRestrictionsConfirmed;
    @JsonProperty("hasDonation")
    public Boolean hasDonation;
    @JsonProperty("steps")
    public List<Step> steps = null;
    @JsonProperty("specialPromotion")
    public List<Object> specialPromotion = null;

    public Optional<Integer> getSeatIdByRowAndColumn(int row, int column){
        return config.auditorium.seatingPlan.stream()
                .filter(seatingPlan -> seatingPlan.row == row)
                .filter(seatingPlan -> seatingPlan.seat == column)
                .findFirst()
                .map(seatingPlan -> seatingPlan.number);
    }
}

