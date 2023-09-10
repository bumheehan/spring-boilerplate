package xyz.bumbing.mvc.event.store;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Instant createdAt;
    @Column(name = "event_type", length = 100)
    private String eventType;
    @Column(name = "payload", columnDefinition = "TEXT")
    private String payload;

    public static SaveEvent create(String eventType, String payload) {
        SaveEvent retVal = new SaveEvent();
        retVal.createdAt = Instant.now();
        retVal.eventType = eventType;
        retVal.payload = payload;
        return retVal;
    }
}
