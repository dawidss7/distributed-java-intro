package exercise3;

import common.Counter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements Counter {
    private AtomicLong counter = new AtomicLong(0);

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public long getValue() {
        return counter.longValue();
    }
}
