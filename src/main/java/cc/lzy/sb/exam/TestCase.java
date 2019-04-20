package cc.lzy.sb.exam;

/**
 * @author chengyu
 * @version 19/4/17
 */
public class TestCase extends Thread {
    private boolean stop = false;

    @Override
    public void run() {
        int i = 0;
        while (!stop) {
            ++i;
        }
        System.out.println("finish loop, i = " + i);
    }

    public void stopLoop() {
        stop = true;
    }

    public boolean isStop() {
        return stop;
    }

    public static void main(String[] args) throws Exception {
        String url = "https://h5.mogujie.com/punish/punish-list.html?violationId=763095";
        String newUrl = url.replace("mogujie.com", "mogu.com");
        System.out.println(newUrl);
    }
}
