package nl.vue.blocker.vueblocker.movies.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class MovieFoundEvent extends ApplicationEvent {

    private Movie movie;

    public MovieFoundEvent(Object source, Movie movie) {
        super(source);
        this.movie = movie;
    }
}
