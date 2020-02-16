package am.poc.legacymicrometer;

import io.micrometer.core.annotation.Timed;
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
        run.getBean(MyService.class).calculateXxx(); //only for testing purposes
    }

    @GetMapping("/xxx")
    @Timed(value = "my.rest.controller") //not necessary spring boot automatically add metrics in your endpoints
    public String callXxx() {
        return service.calculateXxx();
    }


    @GetMapping("/zzz")
    //Timed and Counted not necessary spring boot automatically add metrics in your endpoints
    public String callZzz() {
        return service.calculateZzz();
    }
}
