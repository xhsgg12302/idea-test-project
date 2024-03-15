package _jvm.constant_pool;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/12
 *                          @since  1.0
 *                          @author 12302
 *
 */
import _utils.thread.ThreadUtil;
import com.sun.tools.classfile.ClassFile;
import com.sun.tools.javac.util.Assert;
import sun.reflect.ConstantPool;

import java.io.BufferedInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.sun.tools.classfile.ConstantPool.CONSTANT_Utf8;

public class Bar {
    private static final Method getConstantPool;

    static {
        try {
            getConstantPool = Class.class.getDeclaredMethod("getConstantPool");
            getConstantPool.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    static void butts() {
        try {
            ConstantPool constantPool = (ConstantPool) getConstantPool.invoke(ConstantTest.class);
            // ...
            System.out.println();
            ThreadUtil.keepRunning();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    void checkClassFile(final Path path) throws Exception {
        ClassFile classFile = ClassFile.read(
                new BufferedInputStream(Files.newInputStream(path)));
        for (com.sun.tools.classfile.ConstantPool.CPInfo cpInfo : classFile.constant_pool.entries()) {
            if (cpInfo.getTag() == CONSTANT_Utf8) {
                com.sun.tools.classfile.ConstantPool.CONSTANT_Utf8_info utf8Info = (com.sun.tools.classfile.ConstantPool.CONSTANT_Utf8_info)cpInfo;
                Assert.check(utf8Info.value.length() > 0,
                        "UTF8 with length 0 found at class " + classFile.getName());
            }
        }
    }

    public static void main(String[] args) {
        butts();
    }
}