package agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-26
 * @Desc:
 */
public class MyTransfor implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        /*_12302_2019-07-26_<此处可以静态代理>*/
        System.out.println("from: " + className + "--> hello agent!");


        /*_12302_2019-07-26_<return null 并不表示将要加载的类清空,而是不往其中添加任何东西>*/
        return null;
    }
}
