package xyz.bumbing.mvc.event.external;

import lombok.Getter;
import xyz.bumbing.mvc.event.Event;

@Getter
public class ExternalEvent extends Event {
    private final String topic;
    private final String id;

    public ExternalEvent(String topic, String id) {
        this.topic = topic;
        this.id = id;
    }
}
