package _base.lambda.stream._operator.group1partition;

import _base.lambda.stream.model.Person;
import _base.lambda.stream.utils.StreamUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright 2021 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2021-08-11
 */
public class Demo {

    public static void main(String[] args) {


        List<Person> list = StreamUtils.getList();

        Map<Boolean, List<Person>> partition = list.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 34));

        Map<Integer, List<Person>> group = list.stream().collect(Collectors.groupingBy(Person::getNumberOfWar));

        /* _12302_2021/8/11_< sout > */
        partition.entrySet().stream()
                .forEach(entry-> System.out.println(String.format("%s,%s",entry.getKey(),entry.getValue().size())));

        group.entrySet().stream()
                .forEach(entry-> System.out.println(String.format("%s,%s",entry.getKey(),entry.getValue())));


    }

}
