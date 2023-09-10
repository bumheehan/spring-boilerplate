package xyz.bumbing.mvc.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public class EventPublisher {

    private static ApplicationEventPublisher publisher;

    public static void publish(Event event) {
        if (publisher == null) {
            log.error("Event Not Published");
            return;
        }
        publisher.publishEvent(event);
    }

    public static void setPublisher(ApplicationEventPublisher publisher) {
        EventPublisher.publisher = publisher;
    }
}
