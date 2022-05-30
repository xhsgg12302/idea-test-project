package _base.lambda.functionalInterface.function;

import _base.lambda.stream.model.Person;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class Test {

    public static void main(String[] args) {
        Function<Integer, Integer> f = s -> s++;
        Function<Integer, Integer> g = s -> s * 2;


        /**
         * 类型图解1 Person::getAge
         */
        Function<Person, Integer> function = Person::getAge;
        Integer apply = function.apply(new Person());

        Function<Person, Integer> fucntion1 = person -> person.getAge();
        Function<Person, Integer> function2 = new Function<Person, Integer>() {
            @Override
            public Integer apply(Person person) {
                return person.getAge();
            }
        };



        /**
         * 对象图解2 person.getAge
         */
        Person person = new Person();

        Function<HashMap, String> getAddress = person::getAddress;
        Function<HashMap, String> getAddress2 = map -> person.getAddress(map);
        Function<HashMap, String> getAddress3 = new Function<HashMap, String>() {
            @Override
            public String apply(HashMap hashMap) {
                return person.getAddress(hashMap);
            }
        };
        String apply2 = getAddress.apply(new HashMap());


        /**
         * 其他参考用例
         */
        Function<Person, String> biFunction1 = Person::getAddress;
        BiFunction<Person, Person, String> personPersonStringBiFunction = Person::getAddress;

        String abc = "content";
        /* _12302_2021/4/26_< 类型调用 > */
        BiFunction<String, Object, Boolean> biFunction = String::equals;
        /* _12302_2021/4/26_< 对象调用  abc类型的substring 方法 《==》 str -> str.substring(Integer) */
        Function<Integer, String> biFunction2 = abc::substring;

        Consumer<String> consumer = System.out::println;







        /**
         * 下面表示在执行F时，先执行G，并且执行F时使用G的输出当作输入。
         * 相当于以下代码：
         * Integer a = g.apply(1);
         * System.out.println(f.apply(a));
         */
        System.out.println(f.compose(g).apply(1));

        /**
         * 表示执行F的Apply后使用其返回的值当作输入再执行G的Apply；
         * 相当于以下代码
         * Integer a = f.apply(1);
         * System.out.println(g.apply(a));
         */
        System.out.println(f.andThen(g).apply(1));

        /**
         * identity方法会返回一个不进行任何处理的Function，即输出与输入值相等；
         */
        System.out.println(Function.identity().apply("a"));

    }

}
