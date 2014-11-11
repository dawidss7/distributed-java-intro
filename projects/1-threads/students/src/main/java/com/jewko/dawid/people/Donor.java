package com.jewko.dawid.people;

import com.jewko.dawid.Item;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 09.11.14.
 */
public class Donor implements Runnable {

    private final static String NAME_FORMAT = "Item %d from %s";
    private final static String GOODBYE_FORMAT = "%s says good bye.";
    private final String name;
    private Lock lock = new ReentrantLock();

    public Donor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private int itemCounter = 0;

    private Item getNewItem() {
        return new Item(String.format(NAME_FORMAT, itemCounter++, name));
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Item item = getNewItem();
                while (!Chairman.getInstance().registerProduct(item)) {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                }
                TimeUnit.SECONDS.sleep(new Random().nextInt(25) + 5);
            }
        } catch (InterruptedException e) {
            //time to say goodbye
        } finally {
            System.out.println(String.format(GOODBYE_FORMAT, name));
        }

    }
}
