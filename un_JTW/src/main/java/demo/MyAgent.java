package demo;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-26
 * @Desc: 自己实现代理，通过这个给JVM ->Instruments注册转换器
 *          格式： -javaagent:<jarpath>[=options]
 */
public class MyAgent {

    public static void premain(String args, Instrumentation instrumentation){
        ClassFileTransformer classFileTransformer = new MyTransfor();
        instrumentation.addTransformer(classFileTransformer);
    }
}
