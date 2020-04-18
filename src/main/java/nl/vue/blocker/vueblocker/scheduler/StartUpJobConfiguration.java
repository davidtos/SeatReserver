package nl.vue.blocker.vueblocker.scheduler;

import nl.vue.blocker.vueblocker.movies.acl.MovieAvailableCheckerJob;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

@Configuration
public class StartUpJobConfiguration {

    private JobDetail newJob(String identity, String description) {
        return JobBuilder.newJob().ofType(MovieAvailableCheckerJob.class).storeDurably()
                .withIdentity(JobKey.jobKey(identity))
                .withDescription(description)
                .build();
    }

    private SimpleTrigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup())
                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(1))
                .startNow()
                .build();
    }

    @Bean
    public SchedulerFactoryBean scheduler(DataSource quartzDataSource, SpringBeanJobFactory springBeanJobFactory) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("application.properties"));

        schedulerFactory.setJobFactory(springBeanJobFactory);
        JobDetail jobDetail = newJob("this is my name", "and this is what i do");
        schedulerFactory.setJobDetails(jobDetail);
        schedulerFactory.setTriggers(trigger(jobDetail));
        schedulerFactory.setDataSource(quartzDataSource);
        return schedulerFactory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
}
