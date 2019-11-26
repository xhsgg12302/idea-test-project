package lambda.stream.utils;

import lambda.stream.model.Person;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-01
 * @Desc:
 */
public class StreamUtils {

    public static List<Person> getList(){
        Person person1 = new Person(1l,"李逵",23,new BigDecimal(0.95),10,"沂州沂水县");

        Person person2 = new Person(2l,"扈三娘",17,new BigDecimal(0.90),13,"扈家庄");

        Person person3 = new Person(3l,"柴进",85,new BigDecimal(0.93),11,"沧州横海郡");

        Person person4 = new Person(4l,"林冲",34,new BigDecimal(0.99),15,"东京人氏");

        Person person5 = new Person(5l,"宋江",70,new BigDecimal(0.89),13,"山东省菏泽市郓城县宋家村");

        return Arrays.asList(new Person[]{person1,person2,person3,person4,person5});
    }
}
