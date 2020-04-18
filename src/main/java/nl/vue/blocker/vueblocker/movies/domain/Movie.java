package nl.vue.blocker.vueblocker.movies.domain;

import lombok.*;
import nl.vue.blocker.vueblocker.movies.acl.layout.GeneralMessage;
import nl.vue.blocker.vueblocker.movies.acl.layout.KijkwijzerArray;

import java.io.Serializable;
import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "slug")
public class Movie implements Serializable {
    private Integer id;
    private String oid;
    private String edi;
    private String title;
    private String slug;
    private String description;
    private String genres;
    private String kijkwijzer;
    private String cast;
    private String director;
    private String vueUrl;
    private String trailerUrlLow;
    private String trailerUrlHigh;
    private String trailerYoutubeId;
    private String image;
    private String releaseDate;
    private String specialCategory;
    private Integer playingtime;
    private Integer isEdited;
    private String editDate;
    private Integer vueCreated;
    private String ogTitle;
    private String ogDescription;
    private String ogImage;
    private Integer variantGrouping;
    private String imageRelative;
    private Object imageMissing;
    private Object fullTitle;
    private Object ratingAverage;
    private Object ratingCount;
    private Object stills;
    private List<Performance> performances = null;
    private Object allPerformances;
    private Object variants;
    public List<KijkwijzerArray> kijkwijzerArray = null;
    public List<GeneralMessage> generalMessages = null;
}
