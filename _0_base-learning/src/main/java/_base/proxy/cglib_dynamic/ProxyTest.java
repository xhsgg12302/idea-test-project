package _base.proxy.cglib_dynamic;

import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassWriter;

import java.io.FileOutputStream;
import java.util.Properties;

public class ProxyTest {


    /**
     * -Dcglib.debugLocation=D:\idea-project\idea-test-project/_0_base-learning/target/classes
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception{
        //新建被代理类的对象
        StudentNoIntfs target=new StudentNoIntfs();
        //生成代理类对象
        StudentNoIntfs proxy=(StudentNoIntfs)new CglibProxy(target).createCgLibProxy();
        proxy.sayHello("hello,cglib");
        
        
        System.out.println(proxy.getClass().getName());

        ClassReader classReader = new ClassReader(proxy.getClass().getName());
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES);
        byte[] bytes = classWriter.toByteArray();
        //printClass(bytes);
        


        Properties properties = System.getProperties();



        System.in.read(); //block the program
    }

    public static void printClass(byte[] classFile){
        String path = System.getProperty("user.dir") +  "/_0_base-learning/src/main/java/_base/proxy/cglib_dynamic/proxy.class";
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }
}
