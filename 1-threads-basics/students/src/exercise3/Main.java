package exercise3;

public class Main {

    public static void main(String[] args) {
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(new MyRunnable(), "Thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("FINISHED");
//        for (; ; ) {
//            boolean alive = false;
//            for(Thread thread : threads) {
//                if(thread.isAlive()){
//                    alive=true;
//                }
//            }
//            try {
//                Thread.sleep(7);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if(!alive) {
//                System.out.println("FINISHED");
//                break;
//            }
//        }
    }
}
