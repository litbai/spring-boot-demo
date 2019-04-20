package cc.lzy.sb.test;

import java.util.StringJoiner;

/**
 * @author chengyu
 * @version 19/3/2
 */
public class StringJoinerTest {
    public static void main(String[] args) {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        stringJoiner.add("lzy");
        stringJoiner.add("123");
        stringJoiner.add("456");

        System.out.println(stringJoiner.toString());
    }
}
