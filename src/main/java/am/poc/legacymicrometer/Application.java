package am.poc.legacymicrometer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
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
    }

    @GetMapping("/xxx")
    public String callXxx() {
        service.calculateYyy();
        return service.calculateZZZ();
    }
}
