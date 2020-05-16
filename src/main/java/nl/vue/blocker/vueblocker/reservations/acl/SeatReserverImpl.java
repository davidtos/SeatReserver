package nl.vue.blocker.vueblocker.reservations.acl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.movies.domain.Movie;
import nl.vue.blocker.vueblocker.movies.domain.Performance;
import nl.vue.blocker.vueblocker.reservations.FutureReservation;
import nl.vue.blocker.vueblocker.reservations.SeatReserver;
import org.quartz.*;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class SeatReserverImpl implements SeatReserver {

    private final Scheduler scheduler;

    @Override
    public void reserveSeat(Movie movie, FutureReservation futureReservation, Performance performance) {
        try {
            JobDetail job = reserveJob(movie, futureReservation, performance.getId());
            SimpleTrigger trigger = trigger(job);
            scheduler.scheduleJob(job, trigger);
        } catch (ObjectAlreadyExistsException x) {
            log.error(String.format("Creating a job for a movie and performance that already exists. %s - %s",
                    movie.getTitle(), performance.getId()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private SimpleTrigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup())
                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(5))
                .startNow()
                .build();
    }

    private JobDetail reserveJob(Movie movie, FutureReservation futureReservation, int performanceId) {
        String identity = String.format("%s - %s", movie.getTitle(), performanceId);

        return JobBuilder.newJob().ofType(SeatReservatorJob.class).storeDurably()
                .withIdentity(JobKey.jobKey(identity))
                .usingJobData("movieId", movie.getId())
                .usingJobData("performanceId", performanceId)
                .usingJobData("slugTitle", movie.getSlug())
                .usingJobData("futureReservation", toJson(futureReservation))
                .withDescription("Reserve a performance")
                .build();
    }

    private String toJson(FutureReservation futureReservation) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(futureReservation);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
