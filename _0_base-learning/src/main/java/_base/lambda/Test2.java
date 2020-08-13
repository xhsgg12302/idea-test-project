package _base.lambda;

import _base.lambda.stream.model.Person;
import _base.lambda.stream.utils.StreamUtils;
import org.springframework.jms.core.MessageCreator;

import javax.jms.TextMessage;
import java.lang.invoke.LambdaConversionException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/3/14 17:33
 * @Description:
 */
public class Test2 {

    public static void main1(String[] args) {
        // Java8之前：
        List<String> list1 = Arrays.asList("a", "b", "c", "d");
        for (String str : list1) {
            System.out.println(str);
        }

        // Java 8之后：
        List list2 = Arrays.asList("a", "b", "c", "d");
        list2.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        list2.forEach(System.out::println);

    }

    public static void main2(String[] args) {
        int pageSize = 30;
        int pageNumber = 1;

        List<String> list = Arrays.asList("a", "b", "c", "d","f",
                "h","i","j","k","l",
                "m","n","o","p","q",
                "r","s","t","u","v",
                "w","x","y","z");

        list.stream()
                .skip(pageSize * (pageNumber - 1))
                .limit(pageSize).collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void main3(String[] args) {
        List<String> list1 = Arrays.asList("a", "b", "c", "d");

        String temp = list1.stream()
                .filter(s -> "k".equals(s))
                .findFirst()
                .orElseGet(() -> { list1.add("f") ; return "f";});

        System.err.println(temp);

        list1.forEach(System.out::println);
    }

    public static void main4(String[] args) {
        List<Person> list = StreamUtils.getList();
        list.stream()
                .filter(person -> person.getNumberOfWar() == 13)
                .sorted(Comparator.comparing(Person::getAge))
                .reduce((cp1,cp2)->{cp1.setForceValue(cp1.getForceValue().add(cp2.getForceValue()));return cp1;})
                .orElseGet(()->{Person c = new Person(); c.setNumberOfWar(13); return c;})
                .setNumberOfWar(14);
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.test1();
    }

    public void test1(){
        test2((session)->{
            TextMessage textMessage = session.createTextMessage("{12345678909876543212}");
            return textMessage;
        });
    }

    public void test2(MessageCreator messageCreator){
        if(messageCreator instanceof LambdaConversionException){
            System.out.println(1);
        }

        Class<? extends MessageCreator> aClass = messageCreator.getClass();
        String name = aClass.getName();

        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println(messageCreator);
    }
}
