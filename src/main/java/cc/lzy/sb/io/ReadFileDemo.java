package cc.lzy.sb.io;

import java.lang.reflect.Method;

/**
 * @author chengyu
 * @version 19/4/10
 */
public class ReadFileDemo {
    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("cc.lzy.sb.io.ReflectionDemo");

        Object obj = clazz.newInstance();

        Method m = clazz.getMethod("sayHello", String.class, String.class);

        System.out.println("======================= 分割线 ====================");

        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            m.invoke(obj, i + "", "i love playing basketball");
        }
        System.out.println("line 1: This is branch feature/merged");
        System.out.println("line 2: This is branch feature/merged");
    }
}
