package _jvm.classLoader.define_classloader;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * 这种方法是错误的。
 *
 * public class DemoSonClassLoader extends DemoClassLoader{
 *
 *     @Test
 *     public void test(){
 *         DemoSonClassLoader demoSonClassLoader = new DemoSonClassLoader();
 *         System.out.println(demoSonClassLoader.getParent());
 *     }
 * }
 *
 */


public class DemoSonClassLoader extends ClassLoader{

    public DemoSonClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "/Users/stevenobelia/Desktop/practise-files/sonClassLoader/";
        byte[] clazz = getClassDataByteArray(path,name);
        if(clazz != null){
            return defineClass(name,clazz,0,clazz.length);
        }
        return null;
    }

    private byte[] getClassDataByteArray(String path,String name){
        try(
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                FileInputStream fileInputStream = new FileInputStream(path + name + ".class");
        ) {

            byte[] buffer = new byte[1024];
            int length;
            while((length = fileInputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer,0,length);
            }

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Test 方法中不能存在有参构造器，这儿使用 main 方法
     * @param args
     */

    public static void main(String args[]){
        DemoSonClassLoader demoSonClassLoader = new DemoSonClassLoader(new DemoClassLoader());
        System.out.println(demoSonClassLoader.getParent());


    }
}
