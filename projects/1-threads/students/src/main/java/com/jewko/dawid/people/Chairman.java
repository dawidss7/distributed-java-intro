package com.jewko.dawid.people;

import com.jewko.dawid.Item;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 09.11.14.
 */
public class Chairman implements Runnable {

    private final static String REGISTER_FORMAT = "Registering %s.";
    private final static String WINNER_FORMAT = "Winner for auction %s is %s.";
    private final static String NO_WINNER_FORMAT = "There is no winner for %s.";
    private final static String NO_AUCTIONS = "No auctions within 5 seconds. Closing the market.";
    private final static String GOODBYE = "Chairman says good bye.";
    private Lock lock = new ReentrantLock();
    private Condition emptyQueue = lock.newCondition();
    private boolean auctionStarted = false;

    private Queue<Item> items = new ArrayBlockingQueue<>(10);
    private Queue<Recipient> recipients = new ArrayBlockingQueue<>(10);


    private static Chairman instance;

    private Chairman() {

    }

    public static synchronized Chairman getInstance() {
        if (instance == null) {
            instance = new Chairman();
        }
        return instance;
    }

    public boolean registerProduct(Item item) {
        lock.lock();
        boolean result = false;
        try {
            result = items.offer(item);
            if (result) {
                emptyQueue.signalAll();
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

    public boolean accessForAuction(Recipient recipient) {
        lock.lock();

        boolean result = false;
        try {
            result = auctionStarted && recipients.offer(recipient);
        } finally {
            lock.unlock();
        }
        if (result) {
            System.out.println(String.format(REGISTER_FORMAT, recipient.getName()));
        }
        return result;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Item item;
                try {
                    lock.lock();
                    while (items.isEmpty()) {
                        emptyQueue.await(5, TimeUnit.SECONDS);
                        if (items.isEmpty()) {
                            System.out.println(NO_AUCTIONS);
                            MarketManager.getInstance().requestCloseMarket();
                        }
                    }
                    item = items.poll();
                    auctionStarted = true;
                } finally {
                    lock.unlock();
                }
                TimeUnit.SECONDS.sleep(5);
                lock.lock();
                try {
                    auctionStarted = false;
                    Recipient winner = chooseWinner();
                    if (winner != null) {
                        System.out.println(String.format(WINNER_FORMAT, item.getName(), winner.getName()));
                        winner.addPrize(item);
                    } else {
                        System.out.println(String.format(NO_WINNER_FORMAT, item.getName()));
                    }
                    recipients.forEach(recipient -> recipient.notifyAuctionFinished());
                    recipients.clear();

                } finally {
                    lock.unlock();
                }

            }
        } catch (InterruptedException e) {
            //time to go to bed
        } finally {
            System.out.println(GOODBYE);
        }

    }

    private Recipient chooseWinner() {

        Recipient[] rs = recipients.toArray(new Recipient[0]);
        if (rs.length == 0) {
            return null;
        } else {
            int winner = new Random().nextInt(rs.length);
            return rs[winner];
        }
    }
}
