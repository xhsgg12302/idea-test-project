package _jvm.classLoader.define_classloader;

import org.junit.Test;
import sun.misc.Launcher;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DemoClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "/Users/stevenobelia/Desktop/practise-files/fatherClassloader/";
        byte[] clazz = getClassDataByteArray(path,name);
        if(clazz != null){
            return defineClass(name,clazz,0,clazz.length);
        }
        return null;
    }

    private byte[] getClassDataByteArray(String path,String name){
        try(
              FileInputStream fileInputStream = new FileInputStream(path + name + ".class");
              ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
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


    @Test
    public void test() throws  Exception{
        DemoClassLoader loader = new DemoClassLoader();
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
        System.out.println(loader.getParent().getParent().getParent());
        //loader.loadClass("LoadClass").getMethod("test").invoke(null);


        DemoClassLoader loader2 = new DemoClassLoader();
        Class<?> clazz1 = loader.loadClass("LoadClass");
        Class<?> clazz2 = loader2.loadClass("LoadClass");
        System.out.println(clazz1.hashCode());
        System.out.println(clazz2.hashCode());
        Class<?> clazz3 = loader.findClass("LoadClass");
        //Class<?> clazz4 = loader2.findClass("LoadClass");
        //System.out.println(clazz3.hashCode());
        //System.out.println(clazz4.hashCode());
    }

}
