package _jvm.classASMHandle.transform.abc;

import _jvm.classASMHandle.TestObject;
import _jvm.classASMHandle.utils.ClassLoaderUtil;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Method;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/28
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class MainSplit {
    public static void main(String[] args) throws Exception {
        ClassReader classReader = new ClassReader("_jvm.classASMHandle.TestObject");
        ClassWriter visitorWriterLast = new ClassWriter(0);

        classReader.accept(new EditorClassVisitor(Opcodes.ASM4, visitorWriterLast), 0);

        byte[] byteArray = visitorWriterLast.toByteArray();
        FileUtils.writeByteArrayToFile(
                FileUtils.getFile("_0_base-learning/target/TestObject.class"), byteArray);
        System.out.println("Done");


        // 以下为测试
        Class<?> aClass = ClassLoaderUtil.loadClass(byteArray, "_jvm.classASMHandle.TestObject");
        // 不是同一个类加载加载的，无法转换。
        // TestObject o = (TestObject) aClass.newInstance();
        // o.getValue( 1, "java.home");
        // o.otherTest();

        Object obj = aClass.newInstance();
        Method getValue = aClass.getMethod("getValue", int.class, String.class);
        Object invoke = getValue.invoke(obj, 1, "java.home"); System.out.println( invoke);
        Method otherTest = aClass.getMethod("otherTest");
        otherTest.invoke(obj);
    }
}
