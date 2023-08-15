package xyz.bumbing.mvc.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class InitConfig implements CommandLineRunner {
    private final Environment environment;
    private final CacheManager cacheManager;

    @Override
    public void run(String... strings) {
        log.info("\n\n" + "=========================================================================\n\n");
        log.info("개발 환경: " + environment.getProperty("spring.profiles.active"));
        log.info("Using cache manager: " + cacheManager.getClass().getName());
        log.info("\n\n" + "=========================================================================\n\n");
    }

}