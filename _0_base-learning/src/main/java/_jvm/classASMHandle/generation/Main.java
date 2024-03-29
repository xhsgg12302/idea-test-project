package _jvm.classASMHandle.generation;

import _jvm.classASMHandle.utils.ClassLoaderUtil;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.*;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/28
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class Main {
    /**
     * package pkg;
     *
     * public interface Comparable extends Mesurable {
     *      int LESS = -1;
     *      int EQUAL = 0;
     *      int GREATER = 1;
     *      int compareTo(Object o);
     * }
     */
    public static void main(String[] args) throws Exception {

        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "pkg/Comparable", null, "java/lang/Object",
                null);
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();

        byte[] b = cw.toByteArray();
        FileUtils.writeByteArrayToFile(
                FileUtils.getFile("_0_base-learning/target/Comparable.class"), b);
        System.out.println("Done");

        Class<?> aClass = ClassLoaderUtil.loadClass(b, "pkg.Comparable");
        System.out.println(aClass.getMethods()[0].getName());
    }


}
