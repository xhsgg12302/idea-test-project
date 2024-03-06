package _jvm.classLoader.load_reference;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/3/4
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Entrance {
    public static void main(String[] args) {
        ClassLoader classLoader = new MyClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass("_jvm.classLoader.load_reference.TestClass");

            // 如果不需要实例化的情况，ClassLoader只会加载当前类。Reference not resolve。
            // 放开下面的注释，resolve并且初始化。
            //Object o = clazz.newInstance();
        } catch (Exception e) { throw new RuntimeException(e);}
    }
}
