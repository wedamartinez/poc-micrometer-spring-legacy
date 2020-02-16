package am.poc.legacymicrometer;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    final MyService service;

    public Application(final MyService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        run.getBean(MyService.class).calculateXxx();
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application","legacymetrics");
    }

    @GetMapping("/xxx")
    @Timed(value = "my.rest.controller")
    public String callXxx() {
        return service.calculateXxx();
    }
}
