package _jvm.classASMHandle.utils;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/29
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class ClassLoaderUtil {

    public static Class<?> loadClass(byte[] b, String name) {

        class MyClassLoader extends ClassLoader{
            public Class<?> defineClass(String name, byte[] b){
                return defineClass(name, b, 0, b.length);
            }
        }

        return new MyClassLoader().defineClass(name, b);
    }
}
