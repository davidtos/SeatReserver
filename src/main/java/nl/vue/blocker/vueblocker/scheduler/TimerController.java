package nl.vue.blocker.vueblocker.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/timer")
@AllArgsConstructor
public class TimerController {

    private final SchedulerFactoryBean schedulerFactoryBean;

    @GetMapping
    public void searchForMovie(){

    }

}
