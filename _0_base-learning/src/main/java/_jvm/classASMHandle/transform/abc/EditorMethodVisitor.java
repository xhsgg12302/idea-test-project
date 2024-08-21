package _jvm.classASMHandle.transform.abc;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/28
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class EditorMethodVisitor extends MethodVisitor {

    private String name;

    public EditorMethodVisitor(int api, MethodVisitor mv, String name) {
        super(api, mv);
        this.name = name;
    }

    @Override
    public void visitCode() {
        // 马上要开始读取了
        if("getValue".equals(name)){
            visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            visitLdcInsn("hello");
            visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
    }

    @Override
    public void visitInsn(int opcode) {
        // 在方法返回前，添加 System.out.println("hello");
        if ("otherTest".equals(name) &&
                ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW)) {
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("hello");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        super.visitInsn(opcode);
    }
}
