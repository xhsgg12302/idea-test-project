package _base.proxy;

import _draft.entity.TestBean;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/3/24 23:57
 * @Description:
 */
public class ProxyTest {
    public static void main(String[] args) {
        /*String s = System.getProperty("java.ext.dirs");
        System.out.println(s);*/
        getExtDirs();
    }
    public static void main1(String[] args) {

        //创建一个实例对象，这个对象是被代理的对象
        Person zhangsan = new Student("张三");
        TestBean testBean = new TestBean();
        System.out.println(Person.class.getClassLoader());
        System.out.println(TestBean.class.getClassLoader());

        printClass();

        //创建一个与代理对象相关联的InvocationHandler
        InvocationHandler stuHandler = new StuInvocationHandler(zhangsan);

        //创建一个代理对象stuProxy来代理zhangsan，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuHandler);

        System.out.println(Person.class.getClassLoader());
        //代理执行上交班费的方法
        stuProxy.giveMoney();

        stuProxy.sayHello("HelloWorld");
        /*File[] files = getExtDirs();
        for(File _base.file:files){
            System.out.println(_base.file.getName());
        }*/

    }


    /**
     * 获取Java相关的属性
     *
     * import java.io.File;
     * import java.lang.reflect.InvocationHandler;
     * import java.lang.reflect.Proxy;
     * import java.util.Map;
     * import java.util.Properties;
     * import java.util.Set;
     * import java.util.StringTokenizer;
     * @return
     */
    private static File[] getExtDirs() {
        //加载<JAVA_HOME>/lib/ext目录中的类库
        String s = System.getProperty("java.ext.dirs");
        System.out.println(s);
        Map<String,String> map = System.getenv();
        Properties properties = System.getProperties();
        Set<Object> set = properties.keySet();
        for(Object object :set){
            System.out.println(object.toString()+"\t"+properties.get(object));
        }

        System.out.println("=============================================================");

        for(Map.Entry<String,String> entry:map.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }


        System.out.println(s);
        File[] dirs;
        if (s != null) {
            StringTokenizer st =
                    new StringTokenizer(s, File.pathSeparator);
            int count = st.countTokens();
            dirs = new File[count];
            for (int i = 0; i < count; i++) {
                dirs[i] = new File(st.nextToken());
            }
        } else {
            dirs = new File[0];
        }
        return dirs;
    }

    public static void printClass(){
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", Student.class.getInterfaces());
        String path = "E:/StuProxy.class";
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }
}