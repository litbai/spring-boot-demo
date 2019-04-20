package cc.lzy.sb.util;

import org.springframework.util.StopWatch;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 类型转换Util
 *
 * @author chengyu
 * @version 19/2/22
 */
public class Transformer {
    private Transformer() {
    }

//    public final static <K, V> Map<K, V> listToMap(List<V> values, Predicate<V> predicate, Function<? super V, ? extends K> keyMapper) {
//        if (values == null || values.size() == 0) {
//            return Collections.emptyMap();
//        }
//        if (predicate != null) {
//            return values.stream().filter(predicate).collect(Collectors.toMap(keyMapper, Function.identity(), (x1, x2) -> x1));
//        } else {
//            return values.stream().collect(Collectors.toMap(keyMapper, Function.identity(), (x1, x2) -> x1));
//        }
//    }

    public static void main(String[] args) {

//        IntStream.range(0, 10).forEach(System.out::print);
//
//        List<Integer> counters = Stream.iterate(1, e -> e + 1)
//                .map(Integer::bitCount)
//                .filter(count -> count > 3)
//                .limit(10)
//                .collect(Collectors.toList());
//
//        System.out.println(counters);
        long[] sum = new long[1];
        long begin = System.currentTimeMillis();
//        for (int i = 0; i < 100000; i++) {
//            sum[0] += i;
//        }

        IntStream.range(0, 100000).forEach(e -> sum[0] += e);

        long end = System.currentTimeMillis();
        System.out.println(end - begin);
//        System.out.println(sum[0]);

    }
}
