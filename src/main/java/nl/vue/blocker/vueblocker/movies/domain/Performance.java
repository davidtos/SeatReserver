package nl.vue.blocker.vueblocker.movies.domain;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    private Integer id;
    private String oid;
    private Integer cinemaId;
    private Integer movieId;
    private Integer auditoriumId;
    private Boolean has2d;
    private Boolean has3d;
    private Boolean hasDbox;
    private Boolean hasXd;
    private Boolean hasAtmos;
    private Boolean hasDolbycinema;
    private Object hasHfr;
    private Boolean hasOv;
    private Boolean hasNl;
    private String start;
    private String end;
    private Boolean hasBreak;
    private Boolean visible;
    private Boolean disabled;
    private Boolean isEdited;
    private Integer occupiedSeats;
    private Integer totalSeats;
    private Object price;
    private Object fullPrice;
    private Object reservationFee;
    private Object ticketFee;
    private Object hasRental3dGlasses;
    private Object cinema;
    private Object cinemaName;
    private Object auditoriumName;
    private Object specialCategory;
    private Object variantName;
    private Object variantSlug;
    private Object prices;
    public Integer startTimeInSeconds;
}