package _jvm.classASMHandle.parse;

import org.objectweb.asm.ClassReader;

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
        ClassPrinter classPrinter = new ClassPrinter();
        ClassReader classReader = new ClassReader("_jvm.classASMHandle.TestObject");
        classReader.accept(classPrinter, 0);
    }
}
