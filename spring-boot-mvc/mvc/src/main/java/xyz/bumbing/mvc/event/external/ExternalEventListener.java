package xyz.bumbing.mvc.event.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class ExternalEventListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void requestExternalService(ExternalEvent event) {
        log.info("트랜잭션 끝나고 외부 서비스 요청하는 코드 id : " + event.getId());
    }
}
