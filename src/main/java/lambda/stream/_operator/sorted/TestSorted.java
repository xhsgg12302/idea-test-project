package lambda.stream._operator.sorted;

import lambda.stream.model.Person;
import lambda.stream.utils.StreamUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class TestSorted {


    //1. Comparator.comparing(类::属性一).reversed();
    //2. Comparator.comparing(类::属性一,Comparator.reverseOrder());
    //两种排序是完全不一样的,一定要区分开来 1 是得到排序结果后再排序,2是直接进行排序,很多人会混淆导致理解出错,2更好理解,建议使用

    public static void main(String[] args) {

        List<Person> list = StreamUtils.getList();
        list.forEach(System.out::println);

        //默认按照升序排列（实现Comparable接口|给顶一个Comparator），
        System.out.println("\n=========================默认升序========================================");
        list.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //降序①
        System.out.println("\n=========================降序①========================================");
        list.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //降序②
        System.out.println("\n=========================降序②========================================");
        list.stream()
                .sorted(Comparator.comparing(Person::getAge,Comparator.reverseOrder()))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //多字段排序 1降，2升
        System.out.println("\n=========================多字段排序 1降，2升========================================");
        list.stream()
                .sorted(Comparator.comparing(Person::getNumberOfWar,Comparator.reverseOrder()).thenComparing(Person::getAge))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
