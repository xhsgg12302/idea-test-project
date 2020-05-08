package _base.lambda.stream._operator.min1max;

import _base.lambda.stream.model.Person;
import _base.lambda.stream.utils.StreamUtils;

import java.util.Comparator;
import java.util.List;

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

        //使用min,max
        System.out.println("\n=========================使用min()========================================");
        Person person = list.stream().min(Comparator.comparing(Person::getAge)).get();
        //过时比较器
        //list.stream().min((p1,p2)-> Integer.compare(p1.getAge(),p2.getAge())).get();
        System.out.println(person);


        System.out.println("\n=========================使用max()========================================");
        Person person1 = list.stream().max(Comparator.comparing(Person::getNumberOfWar)).get();
        System.out.println(person1);
    }
}
