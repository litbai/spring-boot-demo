package cc.lzy.sb.exam;


/**
 * @author chengyu
 * @version 19/3/9
 */
public class DateUtil {
    private DateUtil() {}

    public static long currentSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    public static void main(String[] args) {
    }
}
