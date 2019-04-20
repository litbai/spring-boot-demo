package cc.lzy.sb.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author chengyu
 * @version 19/2/28
 */
public class MyInterInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");

        Object result = null;
        if (method.getName().equals("getName")) {
            result =  method.invoke(args);
        }

        System.out.println("after");

        return result;

    }
}
