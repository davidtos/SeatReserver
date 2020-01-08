package nl.vue.blocker.vueblocker.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.acl.movies.Movie;
import nl.vue.blocker.vueblocker.acl.movies.Performance;
import nl.vue.blocker.vueblocker.movies.Movies;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/timer")
@AllArgsConstructor
public class TimerController {

    private final SchedulerFactoryBean schedulerFactoryBean;

    @GetMapping
    public void searchForMovie(){
        SchedulerFactoryBean.getConfigTimeDataSource();
    }

}
