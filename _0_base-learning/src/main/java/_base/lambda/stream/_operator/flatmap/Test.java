package _base.lambda.stream._operator.flatmap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
        Stream<String> s = Stream.of("_draft/test", "t1", "t2", "tee", "abc");
        //s.flatMap(n -> Stream.of(n + "#")).forEach(System.out::print);


        System.out.println("---------");

        List<String> strs = new ArrayList(){{ add("1,2,3"); add("5,6,7");add("20,8");}};

        List<String> collect = strs.stream()
                .flatMap(n -> Stream.of(n.split(",")))
                .distinct()
                .collect(Collectors.toList());


        System.out.println(collect);

        System.out.println();
        //System.out.println("_draft/test".split("").length);
    }
}
