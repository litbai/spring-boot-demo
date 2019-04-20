package cc.lzy.sb.test;

import com.alibaba.fastjson.JSONObject;

import java.util.StringJoiner;

/**
 * Proxy Demo Test
 *
 * @author chengyu
 * @version 19/2/28
 */
public class ProxyDemo {

    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner(" OR ", "liveId(", ")");
        for (int i = 0; i< 10; i++) {
            joiner.add(i + "");
        }
        System.out.println(joiner);
    }


    static class Test {
        private int lottery_participateUv;
        private int lottery_quantity;

        public int getLottery_participateUv() {
            return lottery_participateUv;
        }

        public void setLottery_participateUv(int lottery_participateUv) {
            this.lottery_participateUv = lottery_participateUv;
        }

        public int getLottery_quantity() {
            return lottery_quantity;
        }

        public void setLottery_quantity(int lottery_quantity) {
            this.lottery_quantity = lottery_quantity;
        }
    }
}
