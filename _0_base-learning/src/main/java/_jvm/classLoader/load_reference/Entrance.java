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
            Object o = classLoader.loadClass("_jvm.classLoader.load_reference.TestClass").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) { throw new RuntimeException(e);}
    }
}
