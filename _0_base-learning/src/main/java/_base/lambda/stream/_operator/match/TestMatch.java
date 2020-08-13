package _base.lambda.stream._operator.match;

import _base.lambda.stream.model.Person;
import _base.lambda.stream.utils.StreamUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class TestMatch {

    public static void main1(String[] args) {

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

    public static void main(String[] args) {
        List<String> temp1 = Arrays.asList(new String[]{"1","2","3","4"});
        List<String> temp2 = Arrays.asList(new String[]{"5","6","7","8","9","10"});

        boolean exist4 = temp1.stream().anyMatch((car) -> {
            String tempPlate = String.valueOf(car);
            System.out.println(tempPlate);
            return temp2.stream().anyMatch(orderEnter -> tempPlate.equals(orderEnter));
        });
        System.out.println(exist4);
    }
}
