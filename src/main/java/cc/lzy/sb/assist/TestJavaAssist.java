package cc.lzy.sb.assist;

import javassist.*;

import java.lang.reflect.Method;

/**
 * javaassist test
 *
 * @author chengyu
 * @version 19/4/7
 */
public class TestJavaAssist {
    //创建线程池
    public static ClassPool pool = ClassPool.getDefault();
    public static CtClass clazz = pool.makeClass("Employ");

    public static void test01() throws Exception {

        try {
            //创建属性方法一
            CtField ctFieldOne = CtField.make("private Integer empId;", clazz);
            clazz.addField(ctFieldOne);
            CtField ctFieldTwo = CtField.make("private Integer empAge;", clazz);
            clazz.addField(ctFieldTwo);

            //创建属性方法二
            CtField ctFieldThree = new CtField(pool.get("java.lang.String"), "empName", clazz);
            ctFieldThree.setModifiers(Modifier.PRIVATE);
            clazz.addField(ctFieldThree);

            //创建含参构造器
            CtConstructor constructor = new CtConstructor(new CtClass[]{pool.get("java.lang.Integer"), pool.get("java.lang.String"), pool.get("java.lang.Integer")}, clazz);
            constructor.setBody("{this.empId = $1; this.empName = $2; this.empAge = $3;}");
            clazz.addConstructor(constructor);

            //创建方法方法一
            CtMethod ctMethodOne = CtMethod.make("public Integer getEmpId() {return empId;}", clazz);
            clazz.addMethod(ctMethodOne);
            CtMethod ctMethodTwo = CtMethod.make("public void setEmpId(Integer empId) {this.empId = empId;}", clazz);
            clazz.addMethod(ctMethodTwo);

            //创建方法方法二
            clazz.addMethod(CtNewMethod.getter("getEmpAge", ctFieldTwo));
            clazz.addMethod(CtNewMethod.setter("setEmpAge", ctFieldTwo));

        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
        clazz.writeFile(System.getProperty("user.dir") + "/src/main/java/cc/lzy/sb/assist");
    }

    public static void test02() throws Exception{
        CtClass clazz = pool.get("cc.lzy.sb.assist.TestJavaAssist");
        CtMethod method = clazz.getDeclaredMethod("say");
        System.out.println(method.getReturnType());
    }

    public static int say() {
        System.out.println("hello say!!");
        return 1;
    }

    public static void main(String[] args) {
        try {
//            test01();
//
//            Class clazzEmploy = clazz.toClass();
//
//            //调用含参构造器
//            Object obj = clazzEmploy.getConstructor(Integer.class, String.class, Integer.class).newInstance(1, "Sandy", 8);
//
//            //调用方法
//            Method method = clazzEmploy.getDeclaredMethod("getEmpAge");
//            System.out.println("method.invoke(obj): " + method.invoke(obj));

            test02();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
