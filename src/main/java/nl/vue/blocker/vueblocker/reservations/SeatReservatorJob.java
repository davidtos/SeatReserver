package nl.vue.blocker.vueblocker.reservations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vue.blocker.vueblocker.movies.acl.layout.PerformanceLayout;
import nl.vue.blocker.vueblocker.movies.acl.reserve.Reservation;
import nl.vue.blocker.vueblocker.movies.domain.Cinema;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
@PersistJobDataAfterExecution
public class SeatReservatorJob implements Job {

    private final Cinema cinema;
    private final Scheduler scheduler;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Integer movieId = (Integer) jobExecutionContext.getMergedJobDataMap().get("movieId");
        int performanceId = (int) jobExecutionContext.getMergedJobDataMap().get("performanceId");
        String slugTitle = (String) jobExecutionContext.getMergedJobDataMap().get("slugTitle");
        int seatRow = (int) jobExecutionContext.getMergedJobDataMap().get("seatRow");
        int seatColumn = (int) jobExecutionContext.getMergedJobDataMap().get("seatColumn");
        String sessionId = (String) jobExecutionContext.getMergedJobDataMap().get("sessionId");

        PerformanceLayout cinemaLayout = cinema.getPerformanceLayout(slugTitle, Integer.toString(performanceId));
        Optional<Integer> seatNumber = cinemaLayout.getSeatIdByRowAndColumn(seatRow, seatColumn);

        if (seatNumber.isPresent()) {
            Reservation reservation = reserveSeat(sessionId, performanceId, seatNumber.get());
            if (reservation.success) {
                log.info("Seat is reserved");
                try {
                    JobDetail jobDetail = jobExecutionContext.getJobDetail();
                    jobDetail.getJobDataMap().put("sessionId", reservation.sessionId);
                    scheduler.rescheduleJob(jobExecutionContext.getTrigger().getKey(), trigger(jobDetail));
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            } else {
                log.info("seat could not be reserved");
            }
        } else {
            log.error("Seat number could not be found");
        }
    }

    private Reservation reserveSeat(String sessionId, int performanceId, Integer seatNumber) {
        Reservation reservation = cinema.reserveSeat(performanceId, seatNumber);
        if (!reservation.success) {
            // unreserve seat
            log.info("Going to unreserve seat");
            cinema.unreserveSeat(sessionId, performanceId, seatNumber);
            log.info("unreseverd seat");
            // reserve seat
            log.info("going to reserve seat");
            reservation = cinema.reserveSeat(performanceId, seatNumber);
            log.info("seat reserved");
        }
        return reservation;
    }

    private SimpleTrigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup())
                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForTotalCount(1, 5))
                .startNow() // Creates a loop, good for testing not for production. TODO: start over 5min
                .build();
    }


}
