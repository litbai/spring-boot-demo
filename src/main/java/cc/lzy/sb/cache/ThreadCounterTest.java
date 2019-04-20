package cc.lzy.sb.cache;

import java.util.concurrent.CountDownLatch;

/**
 * @author chengyu
 * @version 19/3/14
 */
public class ThreadCounterTest {
    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        int i = 0;
        while (++i <= 5000) {
            Thread t = new Thread(new Run(), "ttt--" + i);
            t.start();
            System.out.println("ttt--" + i + " started");
        }
    }


    static class Run implements Runnable {

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
