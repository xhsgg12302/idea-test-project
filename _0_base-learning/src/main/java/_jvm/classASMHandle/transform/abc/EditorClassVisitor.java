package _jvm.classASMHandle.transform.abc;

import org.objectweb.asm.ClassVisitor;
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
public class EditorClassVisitor extends ClassVisitor {

    public EditorClassVisitor(int api, ClassVisitor visitorWriterLast) {
        super(api, visitorWriterLast);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        return new EditorMethodVisitor(Opcodes.ASM4, methodVisitor, name);
    }
}
