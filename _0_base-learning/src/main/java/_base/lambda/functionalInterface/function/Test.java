package _base.lambda.functionalInterface.function;

import _base.lambda.stream.model.Person;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class Test {



    /*
        定义一个方法
        参数传一个字符串类型的数组
        参数再传递两个Function接口
            一个泛型使用Function<String, Integer>
            一个泛型使用Function<Integer, String>
     */
    public static void change(String s, Function<String, Integer> fun1, Function<Integer, String> fun2) {
        String ss = fun1.andThen(fun2).apply(s);
        System.out.println(ss);
    }
    public static  void main1(String[] args) {
        // 定义一个字符串
        String s1 = "123";
        // 调用change方法，传递字符串和两个Lambda表达式
        change(s1, (String str) -> {
            // 把字符串转换为整数+10
            return Integer.parseInt(str) + 10;
        }, (Integer i) -> {
            // 把整数转换为字符串。
            return i + ""; // 字符串和整数的求和时：把整数当作字符串，返回结果是两个字符串的拼接
        });
        // 使用Lambda表达式
        change(s1, str -> Integer.parseInt(str) + 10, i -> i + "");
    }

    public static void main(String[] args) {
        Function<Integer, Integer> f = s -> s++;
        Function<Integer, Integer> g = s -> s * 2;


        /**
         * 类型图解1 Person::getAge
         */
        Function<Person, Integer> function = Person::getAge;
        Integer apply = function.apply(new Person());


        /*Function<Person, Integer> funcGetName = Person::getNumberOfWar;
        funcGetName.andThen(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) {

                return null;
            }
        });*/

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
        Supplier<String>          getAddress00 = person::getAddress;
        Supplier<String>          getAddress22 = () -> person.getAddress();
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
