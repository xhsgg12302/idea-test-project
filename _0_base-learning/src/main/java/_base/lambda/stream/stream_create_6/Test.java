package _base.lambda.stream.stream_create_6;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
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

        //a. 创建空的Stream对象
        Stream stream = Stream.empty();

        //b 通过集合类中的stream或者parallelStream方法创建；
        List<String> list = Arrays.asList("a", "b", "c", "d");
        Stream listStream = list.stream();                   //获取串行的Stream对象
        Stream parallelListStream = list.parallelStream();   //获取并行的Stream对象

        //c. 通过Stream中的of方法创建：
        Stream stream1 = Stream.of("a","b","c","d","e");

        //d通过Stream中的iterate方法创建：(另外一个三参没找到)
        Stream stream2 = Stream.iterate(1, n -> n+1).limit(10);
        stream2.forEach(System.out::println);

        //e通过Stream中的generate方法创建
        Stream stream3 = Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });
        Stream.generate(()->Math.random()).limit(10).forEach(System.out::println);

        //f通过Stream中的concat方法连接两个Stream对象生成新的Stream对象
        Stream stream4_1 = Stream.of("a","b","c","d");
        Stream stream4_2 = Stream.of("e","f","g","h");
        Stream.concat(stream4_1,stream4_2).forEach(System.out::println);

        /*Optional<String> o = Optional.ofNullable(s);
        System.out.println(o.orElseThrow(()->new Exception("_draft.test")));*/


        parallelListStream.forEach( c -> System.out.println(c) );

    }
}
