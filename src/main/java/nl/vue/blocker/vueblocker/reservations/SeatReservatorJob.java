package nl.vue.blocker.vueblocker.reservations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.acl.VueApi;
import nl.vue.blocker.vueblocker.acl.layout.PerformanceLayout;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@Component
@AllArgsConstructor
@PersistJobDataAfterExecution
public class SeatReservatorJob implements Job {

    private final VueApi vueApi;
    private final Scheduler scheduler;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Integer movieId = (Integer) jobExecutionContext.getMergedJobDataMap().get("movieId");
        int performanceId = (int) jobExecutionContext.getMergedJobDataMap().get("performanceId");
        String slugTitle = (String) jobExecutionContext.getMergedJobDataMap().get("slugTitle");
        int seatRow = (int) jobExecutionContext.getMergedJobDataMap().get("seatRow");
        int seatColumn = (int) jobExecutionContext.getMergedJobDataMap().get("seatColumn");

        PerformanceLayout cinemaLayout = vueApi.getPerformanceLayout(slugTitle, Integer.toString(performanceId));

        Optional<Integer> seatNumber = cinemaLayout.getSeatIdByRowAndColumn(seatRow, seatColumn);

        seatNumber.ifPresentOrElse(reserveSeat(performanceId), logSeatDoesNotExistError());
        log.info("reserved Seat");

        try {
            scheduler.rescheduleJob(jobExecutionContext.getTrigger().getKey(),trigger(jobExecutionContext.getJobDetail()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private SimpleTrigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup())
                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForTotalCount(1,5))
                .startNow()
                .build();
    }

    private Consumer<Integer> reserveSeat(int performanceId) {
        return integer -> vueApi.reserveSeat(performanceId, integer);
    }

    private Runnable logSeatDoesNotExistError() {
        return () -> log.error("Seat number could not be found");
    }
}
