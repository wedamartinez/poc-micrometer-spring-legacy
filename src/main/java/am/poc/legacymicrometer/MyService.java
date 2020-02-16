package am.poc.legacymicrometer;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    private final Counter counter;

    public MyService(MeterRegistry registry) {
        counter = Counter.builder("myservice.counter")
                .baseUnit("times")
                .description("A normal counter")
                .register(registry);
        counter.increment();
    }

    public String calculateZZZ() {
        counter.increment();
        return "calculated zzz! " + counter.count();
    }

    @Timed
    @Counted
    public void calculateYyy(){
        System.out.println("calculated Yyy!");
    }
}
