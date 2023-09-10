package xyz.bumbing.mvc.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.bumbing.mvc.event.external.ExternalEvent;
import xyz.bumbing.mvc.event.store.SaveEvent;
import xyz.bumbing.mvc.event.store.SaveEventRepository;
import xyz.bumbing.mvc.utils.TransactionHandler;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final SaveEventRepository saveEventRepository;
    private final TransactionHandler transactionHandler;

    //TEST 용도
    @GetMapping("/api/event/create")
    public void createEvent() {
        transactionHandler.runInTransaction(() -> {
            ExternalEvent testEvent = new ExternalEvent("test", "1");
            EventPublisher.publish(testEvent);
            return null;
        });
    }

    @GetMapping("/api/event")
    public Page<SaveEvent> getEvent(Pageable pageable) {
        return saveEventRepository.findAll(pageable);
    }


}
