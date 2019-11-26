package lambda.stream._operator.flatmap;

import java.util.stream.Stream;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class Test {

    public static void main(String[] args) {

        /*<
        将每一个对象通过映射操作转换成流后追加到主流，然后关闭自己
        //out: testt1t2teeabc
        >_12302_2019-10-02_*/
        Stream<String> s = Stream.of("test", "t1", "t2", "tee", "abc");
        s.flatMap(n -> Stream.of(n.split(""))).forEach(System.out::print);


        System.out.println();
        System.out.println("test".split("").length);
    }
}
