package cn.danielw.hpa.demo;

import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class DemoController {

    @Autowired
    @Qualifier("appTestCounter")
    private Counter appTestCounter;

    @Autowired
    @Qualifier("externalTestGaugeValue")
    private AtomicInteger externalTestGaugeValue;

    @GetMapping("/test_increase_counter")
    public String test() throws InterruptedException {
        appTestCounter.increment();
        return "OK";
    }

    @GetMapping("/test_set_gauge")
    public String fakeExternalQueueDepth(@RequestParam("length") int length) {
        externalTestGaugeValue.set(length);
        return "OK";
    }

    @GetMapping("/fakeCPULoad")
    public String fakeCPULoad(@RequestParam("minutes") int minutes) {
        long t1 = System.currentTimeMillis();
        while (System.currentTimeMillis() - t1 < 60L * 1000 * minutes) {
            for (int i = 0; i < 100000; i++) {
                double x = i * 3.1416;
                x = Math.sqrt(x);
                x = x * x * i;
            }
            System.out.println("I am busy");
        }
        return "OK";
    }



}
