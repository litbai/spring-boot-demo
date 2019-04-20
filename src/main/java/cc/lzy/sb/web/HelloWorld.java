package cc.lzy.sb.web;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * 第一个sb应用
 *
 * @author chengyu
 * @version 19/1/27
 */
@RestController
public class HelloWorld {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${placeholder.custom}")
    private String placeholder;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World ABCD!";
    }

    @RequestMapping("/thread")
    public Object threadTrace() {
        return JSONObject.toJSONString(Thread.getAllStackTraces());
    }

    @RequestMapping("/placeholder")
    public String placeholder() {
        logger.warn("this is warn : " + placeholder);
        return placeholder;
    }

    public static class Sort {
        private int id;
        private int age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static class Consumer implements Runnable {

        private Future future;

        public Consumer(Future future) {
            this.future = future;
        }

        @Override
        public void run() {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Producer<T> implements Callable<T> {

        public T t;

        public Producer(T t) {
            this.t = t;
        }

        @Override
        public T call() throws Exception {
            Thread.sleep(3000);
            return t;
        }
    }
}
