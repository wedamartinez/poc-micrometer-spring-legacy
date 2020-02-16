package am.poc.legacymicrometer;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyService {

    private MeterRegistry meterRegistry;

    public MyService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Timed
    @Counted
    public List<String> calculateXxx() {
        exampleTwoOfToCreateACounters();
        exampleThreeToCreateACounters();
        final List<Meter> meters = meterRegistry.getMeters();
        return meters.stream().map(meter ->
                meter.toString() + " # # # # " + meter.getId().toString() + " # # # # " + meter.measure().toString())
                .collect(Collectors.toList());
    }

    //NOT NECESSARY WITH ANNOTATIONS OPTION ONLY AS EXAMPLE OF OTHER WAYS TO DO A NEW METER
    private void exampleTwoOfToCreateACounters() {
        Metrics.counter("service.myservice.exampleTwo").increment();
    }

    //NOT NECESSARY WITH ANNOTATIONS OPTION ONLY AS EXAMPLE OF OTHER WAYS TO DO A NEW METER
    private void exampleThreeToCreateACounters() {
        final Counter counter = Counter.builder("service.myservice.exampleThree")
                .baseUnit("Units")
                .description("This is a description")
                .register(meterRegistry);
        counter.increment();
    }
}
