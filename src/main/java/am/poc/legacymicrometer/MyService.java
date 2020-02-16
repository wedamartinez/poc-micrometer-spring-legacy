package am.poc.legacymicrometer;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
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
        final List<Meter> meters = meterRegistry.getMeters();
        return meters.stream().map(meter ->
                meter.toString() + " /////// " + meter.getId().toString() + " /////// " + meter.measure().toString())
                .collect(Collectors.toList());
    }
}
