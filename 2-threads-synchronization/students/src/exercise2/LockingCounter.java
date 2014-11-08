package exercise2;

import common.Counter;

import java.util.concurrent.locks.ReentrantLock;

public class LockingCounter implements Counter {
    private long counter = 0;
    public final ReentrantLock lock = new ReentrantLock();
    public final ReentrantLock lock2 = new ReentrantLock();

    @Override
    public void increment() {
        lock.lock();
        lock2.lock();
        try {
            counter++;
        } finally {
            lock2.unlock();
            lock.unlock();
        }

    }

    @Override
    public long getValue() {
        return counter;
    }
}
