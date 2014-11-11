package com.jewko.dawid.people;

import com.jewko.dawid.Item;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 09.11.14.
 */
public class Recipient implements Runnable {

    private final static String WON_FORMAT = "%s won %s";
    private final static String GOODBYE_FORMAT = "%s says good bye leaving with items %s.";
    private Lock lock = new ReentrantLock();
    private Condition auctionFinished = lock.newCondition();
    private final String name;
    private List<Item> items = new LinkedList<>();
    private boolean winner = false;


    public Recipient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPrize(Item item) {
        items.add(item);
        System.out.println(String.format(WON_FORMAT, name, item.getName()));
        winner = true;
    }

    public void notifyAuctionFinished() {
        lock.lock();
        try {
            auctionFinished.signalAll();
        } finally {
            lock.unlock();
        }
    }


    @Override
    public void run() {
        lock.lock();
        try {

            while (!Thread.currentThread().isInterrupted()) {
                do {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                } while (!Chairman.getInstance().accessForAuction(this));
                auctionFinished.await();
                if (winner) {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    winner = false;
                }
            }
        } catch (InterruptedException e) {
            //time to say goodbye ;-)
        } finally {
            lock.unlock();
            System.out.println(String.format(GOODBYE_FORMAT, name, items.toString()));
        }

    }
}
