package lambda.stream._operator.map;

import lambda.stream.model.Person;
import lambda.stream.utils.StreamUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class TestMap {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数   这儿  ++i 和 i++ 结果不一样
        List<Integer> squaresList = numbers.stream().map( i -> ++i).collect(Collectors.toList());

        squaresList.forEach(System.out::println);


        List<Person> list = StreamUtils.getList();

        list.stream()
                .map(Person::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        list.stream()
                .map(Person::getName)
                .map(String::length)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
