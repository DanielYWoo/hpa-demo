package cn.danielw.hpa.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class DemoConfig {

    @Autowired
    private MeterRegistry meterRegistry;

    @Bean("appTestCounter")
    public Counter appTestCounter() {
        return Counter.builder("my_test_counter")
                .description("Number of visits to the site")
                .register(meterRegistry);
    }

    @Bean("externalTestGaugeValue")
    public AtomicInteger externalTestGaugeValue() {
        return new AtomicInteger(0);
    }

    @Bean("externalTestGauge")
    public Gauge externalTestGauge(@Autowired @Qualifier("externalTestGaugeValue") AtomicInteger gaugeValue) {
        return Gauge.builder("my_external_gauge", gaugeValue, AtomicInteger::get)
                .description("job queue depth")
                .register(meterRegistry);
    }

}
