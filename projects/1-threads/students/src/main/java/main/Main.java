package main;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dawid Jewko <dawid.jewko@gmail.com> on 08.11.14.
 */
public class Main {
    public static void main(String[] args) {
        //MARKET MANAGER
        MarketManager manager = MarketManager.getInstance();

        //REGISTER DONORS
        for(int i=1; i<=1; i++){
            manager.registerParticipant(new Donor("Donor "+i));
        }

        //REGISTER RECIPIENTS
        for(int i=1; i<=10; i++){
            manager.registerParticipant(new Recipient("Recipient "+i));
        }

        //ENJOY THE SHOW
        manager.startMarket();

        //TO AVOID ENDLESS AUCTION
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            //ups
        }

        manager.requestCloseMarket();
    }
}
