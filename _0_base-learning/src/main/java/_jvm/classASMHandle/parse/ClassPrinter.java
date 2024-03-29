package _jvm.classASMHandle.parse;

import org.objectweb.asm.*;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/28
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class ClassPrinter extends ClassVisitor {

    public ClassPrinter() { super(Opcodes.ASM4);}

    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }
    public void visitSource(String source, String debug) {
    }
    public void visitOuterClass(String owner, String name, String desc) {
    }
    public AnnotationVisitor visitAnnotation(String desc,
                                             boolean visible) {
        return null;
    }
    public void visitAttribute(Attribute attr) {
    }
    public void visitInnerClass(String name, String outerName,
                                String innerName, int access) {
    }
    public FieldVisitor visitField(int access, String name, String desc,
                                   String signature, Object value) {
        System.out.println(" " + desc + " " + name);
        return null;
    }
    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        return null;
    }
    public void visitEnd() {
        System.out.println("}");
    }
}
