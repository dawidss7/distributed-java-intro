package com.jewko.dawid.people;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 09.11.14.
 */
public class MarketManager {

    private static MarketManager instance;

    private MarketManager() {

    }

    public static synchronized MarketManager getInstance() {
        if (instance == null) {
            instance = new MarketManager();
        }
        return instance;
    }

    ExecutorService es = Executors.newCachedThreadPool();
    List<Runnable> persons = new LinkedList();

    public void registerParticipant(Runnable participant) {
        persons.add(participant);
    }

    public void startMarket() {
        persons.add(Chairman.getInstance());
        persons.forEach(person -> es.execute(person));
    }

    public void requestCloseMarket() {
        es.shutdownNow();
    }

}
