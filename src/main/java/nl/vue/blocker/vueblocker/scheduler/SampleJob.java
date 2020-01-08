package nl.vue.blocker.vueblocker.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SampleJob implements Job {


    public void execute(JobExecutionContext context) {
        log.error("see me going");
    }
}