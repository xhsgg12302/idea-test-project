package lambda.stream._operator.match;

import lambda.stream.model.Person;
import lambda.stream.utils.StreamUtils;

import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class TestMatch {

    public static void main(String[] args) {

        List<Person> list = StreamUtils.getList();
        list.forEach(System.out::println);

        //使用anyMatch
        System.out.println("\n=========================使用anyMatch()========================================");
        boolean exist = list.stream().anyMatch(person -> "林冲".equals(person.getName()));
        System.out.println(exist);

        //使用allMatch
        System.out.println("\n=========================使用allMatch()========================================");
        boolean exist2 = list.stream().allMatch(person -> 15 < person.getAge());
        System.out.println(exist2);

        //使用noneMatch
        System.out.println("\n=========================使用noneMatch()========================================");
        boolean exist3 = list.stream().noneMatch(person -> "12302".equals(person.getName()));
        System.out.println(exist3);
    }
}
