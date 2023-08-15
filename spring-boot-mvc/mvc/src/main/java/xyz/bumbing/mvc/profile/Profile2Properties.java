package xyz.bumbing.mvc.profile;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
@ConfigurationProperties(prefix = "profile2")
public class Profile2Properties {
    private String value;
    private String defaultValue;

    @PostConstruct
    public void init() {
        log.info("profile2 check : " + this.value);
        log.info("profile2 default check : " + this.defaultValue);
    }
}
