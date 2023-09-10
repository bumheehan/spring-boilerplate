package xyz.bumbing.mvc.event.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.bumbing.mvc.error.BusinessException;
import xyz.bumbing.mvc.event.Event;

import static xyz.bumbing.mvc.error.ErrorCode.INVALID_PARAMETER;

@Service
@RequiredArgsConstructor
public class SaveEventService {
    private final SaveEventRepository saveEventRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void save(Event event) {
        SaveEvent saveEvent = SaveEvent.create(event.getClass().getName(), toJson(event));
        saveEventRepository.save(saveEvent);
    }

    private String toJson(Event event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new BusinessException("json parse error", e, INVALID_PARAMETER);
        }
    }

}
