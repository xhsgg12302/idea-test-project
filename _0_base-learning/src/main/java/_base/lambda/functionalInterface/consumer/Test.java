package _base.lambda.functionalInterface.consumer;

import java.util.function.Consumer;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class Test {
    public static void main(String[] args) {
        Consumer f = System.out::println;
        Consumer f2 = n -> System.out.println(n + "-F2");

        //执行完F后再执行F2的Accept方法
        /*<增强predicate后返回一个Consumer,>_12302_2019-10-02_*/
        f.andThen(f2).accept("_draft/test");

        //连续执行F的Accept方法
        f.andThen(f).andThen(f).andThen(f).accept("test1");
    }
}
