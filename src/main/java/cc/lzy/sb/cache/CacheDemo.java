package cc.lzy.sb.cache;

import com.google.common.cache.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Guava Cache Demo
 *
 * @author chengyu
 * @version 19/2/11
 */
public class CacheDemo {
    public static void main(String[] args) throws Exception {

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(300)
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .refreshAfterWrite(30, TimeUnit.SECONDS)
                .recordStats()
                .concurrencyLevel(1)
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        System.out.println(notification.getKey() + "=" + notification.getValue() + ", cause: " + notification.getCause());
                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        Thread.sleep(1000);
                        return System.currentTimeMillis() + "";
                    }

                    @Override
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        return super.reload(key, oldValue);
                    }
                });

        int data1 = '?';
        int data2 = '\r';
        int data3 = '\n';
        System.out.println(data1);
        System.out.println(data2);
        System.out.println(data3);
    }
}
