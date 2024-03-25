package _jvm.classLoader.load_reference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/3/4
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class MyClassLoader extends ClassLoader{

    /**
     * 加载类的引用类时会用当前初始类加载器去加载。
     *
     * @param name
     *         The <a href="#name">binary name</a> of the class
     *
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // Ignore this for the example as Object is always implicitly referenced by a class
        if("java.lang.Object".equals(name)) {
            return super.loadClass(name);
        }
        System.out.println("Loading class: " + name);

        // Load class from "/path/to/my/classes/TestClass.class"
        String pathPrefix = "_0_base-learning/src/main/java/_jvm/classLoader/load_reference/compiled_classes/";
        String fileName = name.substring(name.lastIndexOf(".") + 1);
        Path fileLocation = Paths.get(pathPrefix + fileName + ".class");
        try {
            byte[] classData = Files.readAllBytes(fileLocation);
            Class<?> clazz = defineClass(name, classData, 0, classData.length);
            // Class has been successfully loaded and defined by the classloader
            System.out.println("Loaded: " + clazz.getSimpleName());
            return clazz;
        } catch (IOException e) {
            // Class could not be found
            throw new ClassNotFoundException();
        }
    }
}
