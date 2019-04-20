package cc.lzy.sb.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chengyu
 * @version 19/3/11
 */
public class VolatileDemo {


    private static ExecutorService service = Executors.newFixedThreadPool(3);


    public static void main(String[] args) throws Exception {
        service.submit(new Job());
        service.submit(new Job());

        service.shutdown();

        service.submit(new Job());
        service.submit(new Job());



        service.submit(new Job());
        service.submit(new Job());
    }


    static class Job implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


