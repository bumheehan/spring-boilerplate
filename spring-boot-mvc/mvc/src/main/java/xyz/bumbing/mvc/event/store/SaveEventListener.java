package xyz.bumbing.mvc.event.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import xyz.bumbing.mvc.event.Event;

@Component
@Slf4j
@RequiredArgsConstructor
public class SaveEventListener {

    private final SaveEventService saveEventService;


    //기존 트랜잭션 사용
    @EventListener
    public void saveEvent(Event event) {
        log.info("SaveEventListener.saveEvent");
        saveEventService.save(event);
    }
}
