package _jvm.classASMHandle.transform;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.*;

import java.io.IOException;

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
    public static void main(String[] args) throws IOException {

        ClassReader classReader = new ClassReader("_jvm.classASMHandle.TestObject");
        ClassWriter classWriter = new ClassWriter(0);

        classReader.accept(new ClassVisitor(Opcodes.ASM4, classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
                return new MethodVisitor(Opcodes.ASM4, methodVisitor) {
                    @Override
                    public void visitCode() {
                        if("getValue".equals(name)){
                            visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            visitLdcInsn("hello");
                            visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                            visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            visitLdcInsn("hello");
                            visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

                        }
                    }
                };
            }
        }, 0);

        byte[] byteArray = classWriter.toByteArray();
        FileUtils.writeByteArrayToFile(
                FileUtils.getFile("_0_base-learning/target/TestObject.class"), byteArray);
        System.out.println("Done");
    }
}
