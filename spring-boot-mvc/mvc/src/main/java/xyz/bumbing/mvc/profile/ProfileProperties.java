package xyz.bumbing.mvc.profile;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
@ConfigurationProperties(prefix = "profile")
public class ProfileProperties {
    private String value;
    private String defaultValue;

    @PostConstruct
    public void init() {
        log.info("profile check : " + this.value);
        log.info("profile default check : " + this.defaultValue);
    }
}
