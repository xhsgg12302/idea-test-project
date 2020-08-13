package _base.lambda.stream._operator.skip;

import _base.lambda.stream.model.Person;
import _base.lambda.stream.utils.StreamUtils;

import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class TestSkip {
    public static void main(String[] args) {

        List<Person> list = StreamUtils.getList();
        list.forEach(System.out::println);


        //使用skip(3)
        System.out.println("\n=========================使用skip(3))========================================");
        list.stream().skip(3).forEach(System.out::println);
    }
}
