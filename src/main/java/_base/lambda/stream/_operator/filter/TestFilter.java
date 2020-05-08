package _base.lambda.stream._operator.filter;

import _base.lambda.stream.model.Person;
import _base.lambda.stream.utils.StreamUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-01
 * @Desc:
 */
public class TestFilter {

    public static void main(String[] args) {

        List<Person> list = StreamUtils.getList();
        list.forEach(System.out::println);

        //年龄大于30的
        System.out.println("\n=========================年龄大于30的========================================");
        List<Person> collect = list.stream().filter(person -> person.getAge() > 30).collect(Collectors.toList());
        collect.forEach(System.out::println);


        //地址包含'县'字的
        System.out.println("\n=========================地址包含'县'字的=============================================");
        List<Person> collect1 = list.stream().filter(person -> person.getAddress().contains("县")).collect(Collectors.toList());
        collect1.forEach(System.out::println);
    }

}
