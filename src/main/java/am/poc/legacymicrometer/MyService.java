package am.poc.legacymicrometer;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    //"Manual option" injecting MeterRegistry
    private final Counter counter;

    public MyService(MeterRegistry registry) {
        counter = Counter.builder("myservice.counter")
                .baseUnit("times")
                .description("A normal counter")
                .register(registry);
    }

    //"Aop option" using spring-aspects and TimedAspect in TimedConfiguration.java
    @Counted
    @Timed
    public String calculateXxx() {
        counter.increment();
        System.out.println("Number of calculations: " + counter.count());
        return "calculated Xxx! " + counter.count();
    }

    @Counted
    public String calculateZzz() {
        return "calculated Zzz!";
    }
}
