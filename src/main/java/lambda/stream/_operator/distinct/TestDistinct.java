package lambda.stream._operator.distinct;

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
public class TestDistinct {

    public static void main(String[] args) {
        //去重基本类型
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);


        //去重对象（需要多加一个）
        List<Person> list = StreamUtils.getList();
        list.stream().distinct().forEach(System.out::println);
    }
}
