package cc.lzy.sb.io;

import com.google.common.base.Splitter;
import org.apache.catalina.util.URLEncoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.digester.DocumentProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *  * Created by yuanlang on 6/9/15.
 *  
 */
public class LogUtil {
    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);
    //非操作业务简要输出
    public static final String NON_OPERATE = "[NON_OPERATE]message[{}]param[{}],return[{}]";
    //操作业务简要输出
    public static final String OPERATE = "[OPERATE]message[{}]param[{}],return[{}]";
    //操作
    private static final String OPT_PREF = "[OPERATE]";
    //描述信息长度控制
    private static final int DESCRIPTION_LENGTH = 200;
    //异常堆栈深度控制
    private static final int STACKTRACE_DEFAULT_DEPTH = 10;

    private static StringBuilder result = new StringBuilder();

    /**
     * INFO级日志格式
     * <p/>
     * 说明：INFO/DEBUG适用
     * 用法：orderId（必需，否则抛异常），description（必需，否则抛异常，长度超过200则自动截断）
     * 返回：String
     * 用例：logger.info(LogUtil.infoOptLogDesc(12345678, "Test desc", testEntity, testString));
     * 注意：对象内部需要覆盖toString()方法，才能查看实体内部属性情况，可以系统生成或手动输入，但是不可以使用JSON的toJSONString()方法
     *
     * @param orderId
     * @param description
     * @param params
     * @param <T>
     * @return
     */
    public static <T> String infoOptLogDescNew(long orderId, String description, T... params) {
        checkParameter(orderId, description);
        if (description.length() > DESCRIPTION_LENGTH) {
            description = description.substring(0, DESCRIPTION_LENGTH) + "...";
        }
        StringBuilder des = new StringBuilder(OPT_PREF).append(",").append(orderId).append(",").append(description);
        for (Object object : params) {
            if (object == null) {
                continue;
            }
            des.append(",").append(descRef(object));
        }
        return des.toString();
    }

    /**
     * ERROR级日志格式（无异常堆栈）
     * <p/>
     * 说明：ERROR/WARN适用，无需打印异常堆栈信息
     * 用法：orderId（必需，否则抛异常），description（必需，否则抛异常，长度超过200则自动截断）
     * 返回：String
     * 用例：logger.error(LogUtil.errorOptLogDesc(12345678, "Test desc", testEntity, testString));
     * 注意：对象内部需要覆盖toString()方法，才能查看实体内部属性情况，可以系统生成或手动输入，但是不可以使用JSON的toJSONString()方法
     *
     * @param orderId
     * @param description
     * @param params
     * @param <T>
     * @return
     */
    public static <T> String errorOptLogDesc(long orderId, String description, T... params) {
        checkParameter(orderId, description);
        if (description.length() > DESCRIPTION_LENGTH) {
            description = description.substring(0, DESCRIPTION_LENGTH) + "...";
        }
        StringBuilder des = new StringBuilder(OPT_PREF).append(",").append(orderId).append(",").append(description);
        for (Object object : params) {
            if (object == null) {
                continue;
            }
            des.append(",").append(object.toString());
        }
        return des.toString();
    }

    /**
     * ERROR级日志格式（有异常堆栈）
     * <p/>
     * 说明：说明：ERROR/WARN适用，需要打印异常堆栈信息
     * 用法：orderId（必需，否则抛异常），description（必需，否则抛异常，长度超过200则自动截断），t（必需，否则抛异常，异常堆栈打印深度设为10，向后截断），params（参数表，任意类型对象，任意个数，内部实现toString方法）
     * 返回：String
     * 用例：logger.error(LogUtil.errorOptLogDesc(12345678, "Test desc", e, testEntity, testString));
     * 注意：对象内部需要覆盖toString()方法，才能查看实体内部属性情况，可以系统生成或手动输入，但是不可以使用JSON的toJSONString()方法
     *
     * @param orderId
     * @param description
     * @param t
     * @param params
     * @param <T>
     * @return
     */
    public static <T> String errorOptLogDesc(long orderId, String description, Throwable t, T... params) {
        checkParameter(orderId, description);
        if (description.length() > DESCRIPTION_LENGTH) {
            description = description.substring(0, DESCRIPTION_LENGTH) + "...";
        }
        StringBuilder des = new StringBuilder(OPT_PREF).append(",").append(orderId).append(",").append(description);
        for (Object object : params) {
            if (object == null) {
                continue;
            }
            des.append(",").append(object.toString());
        }
        StackTraceElement[] ste = t.getStackTrace();
        if (ste != null) {
            des.append(",").append(t.getCause()).append(",").append(t.getMessage());
            int lengthLimit = STACKTRACE_DEFAULT_DEPTH;
            if (ste.length < STACKTRACE_DEFAULT_DEPTH) {
                lengthLimit = ste.length;
            }
            for (int i = 0; i < lengthLimit; i++) {
                des.append(",").append(ste[i].toString());
            }
        }
        return des.toString();
    }

    /**
     * 参数校验
     *
     * @param orderId
     * @param description
     */
    private static void checkParameter(long orderId, String description) {
        if (orderId <= 0L) {
            throw new IllegalArgumentException("orderId must greater than 0");
        }
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("description cannot be null");
        }
    }

    /**
     * 递归遍历类及父类的属性值
     *
     * @param o
     * @param _class
     * @return
     */
    private static Class<?> recurRef(Object o, Class<?> _class) {
        if (_class == null) {
            return null;
        } else {
            result.append("[").append(_class.getName()).append("]");
            Method[] methods = _class.getDeclaredMethods();// 获得类的方法集合
            // 遍历方法集合
            for (Method method : methods) {
                // 获取所有getXX()的返回值
                if (method.getName().startsWith("get")) {// 方法返回方法名
                    method.setAccessible(true);// 允许private被访问(以避免private
                    Object object;
                    try {
                        object = method.invoke(o, null);
                        result.append(",").append(method.getName()).append("=").append(object);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            result.append(",");
            return recurRef(o, _class.getSuperclass());
        }
    }

    /**
     * @param object
     */
    private static String descRef(Object object) {
        Class<? extends Object> clazz = object.getClass();
        recurRef(object, clazz);
        result.append(object.hashCode());
        return result.toString();
    }

    public static void main(String[] args) {
        String encode = URLEncoder.DEFAULT.encode("欧式李泽洋ciafaofcqw", StandardCharsets.UTF_8);
        String encode2 = URLEncoder.DEFAULT.encode(encode, StandardCharsets.UTF_8);

        System.out.println(encode);
        System.out.println(encode2);

        System.out.println(URLDecoder.decode("%25"));
    }

}