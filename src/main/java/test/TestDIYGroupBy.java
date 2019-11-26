package test;

import lambda.stream.model.Person;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-07
 * @Desc:
 */
public class TestDIYGroupBy {

    public static List<Person> get(){
        Person person1 = new Person(1l,"李逵",23,new BigDecimal(0.95),3,"沂州沂水县");

        Person person2 = new Person(2l,"扈三娘",17,new BigDecimal(0.90),3,"扈家庄");

        Person person3 = new Person(3l,"柴进",85,new BigDecimal(0.93),3,"沧州横海郡");

        Person person4 = new Person(4l,"林冲",34,new BigDecimal(0.99),3,"东京人氏");

        Person person5 = new Person(5l,"宋江",70,new BigDecimal(0.89),3,"山东省菏泽市郓城县宋家村");

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);

        return list;
    }

    public static void main(String[] args) {
        List<Person> list = get();

        list.stream()
                .filter(person -> person.getNumberOfWar() == 1)
                .sorted(Comparator.comparing(Person::getAge))
                .reduce((person,person2)->{person.setForceValue(person.getForceValue().add(person2.getForceValue()));return person;})
                .orElseGet(()->{Person p = new Person(); p.setNumberOfWar(1); return p;})
                .setNumberOfWar(2);
        list.stream()
                .filter(person -> person.getNumberOfWar() != 1)
                .sorted(Comparator.comparing(Person::getForceValue))
                .collect(Collectors.toList())
                .forEach(System.out::println);



    }
}
