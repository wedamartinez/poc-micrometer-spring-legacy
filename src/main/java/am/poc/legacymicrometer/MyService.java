package am.poc.legacymicrometer;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class MyService {


    private final Counter counter;

    public MyService(MeterRegistry registry) {
        counter = registry.counter("my.calculation.constructor");
        counter.increment();
    }

    @Counted("my.calculation")
    public String calculateXxx() {
        counter.increment();
        System.out.println("called calculated!");
        return "calculated!";
    }
}
