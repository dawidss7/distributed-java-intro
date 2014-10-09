package exercise4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(4);
        for(int i=0; i<=4; i++){
            es.execute(new MyRunnable());
        }
        es.shutdown();
        try {
            es.awaitTermination(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FINISHED");
    }
}
