package lambda.stream._operator.limit;

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
public class TestLimit {

    public static void main(String[] args) {

        List<Person> list = StreamUtils.getList();
        list.forEach(System.out::println);


        //使用limit()
        System.out.println("\n=========================使用limit(3)========================================");
        list.stream().limit(3).forEach(System.out::println);
    }

}
