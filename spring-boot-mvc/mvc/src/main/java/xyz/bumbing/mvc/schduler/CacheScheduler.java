package xyz.bumbing.mvc.schduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class CacheScheduler {

    @Scheduled(cron = "0 * * * * *")
    public void scheduler() {
        log.info("schedule - START");
        long start = System.currentTimeMillis();
        log.info("schedule - FINISH , time : " + (System.currentTimeMillis() - start) + ", next execute time : " + nextExecuteTime("0 5/30 * * * *"));
    }

    public ZonedDateTime nextExecuteTime(String cron) {
        CronExpression cronTrigger = CronExpression.parse(cron);
        return cronTrigger.next(ZonedDateTime.now());
    }

}