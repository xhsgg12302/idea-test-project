package lambda.stream._operator.list1map;

import lambda.stream.model.Person;
import lambda.stream.utils.StreamUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class Test {

    public static void main(String[] args) {

        List<Person> list = StreamUtils.getList();
        list.forEach(System.out::println);

        //list->map
        System.out.println("\n=========================list->map key-value(对象)========================================");
        Map<String,Object> map = list.stream().collect(Collectors.toMap(Person::getName, person -> person));
        map.forEach((k,v) -> System.out.println( k + "\t " + v));

        System.out.println("\n=========================list->map key-value(对象属性)========================================");
        Map<String,Object> map1 = list.stream().collect(Collectors.toMap(Person::getName, person -> person.getAddress()));
        //-----------------------------------------------------------------person -> person.getAddress()<==>Person::getAddress
        map1.forEach((k,v) -> System.out.println( k + "\t " + v));

        System.out.println("\n=========================list->map key-value(key duplicate)========================================");
        Map<Integer,Object> map2 = list.stream().collect(Collectors.toMap(Person::getNumberOfWar, person -> person ,(k1,k2) -> k2 ));
        map2.forEach((k,v) -> System.out.println( k + "\t " + v));

    }
}
