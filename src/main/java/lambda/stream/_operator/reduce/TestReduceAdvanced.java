package lambda.stream._operator.reduce;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-03
 * @Desc:
 */
public class TestReduceAdvanced {


    public static void main(String[] args) {
        Stream<String> stream = Stream.of("aa", "ab", "c", "ad");

        stream.reduce(new ArrayList<>(), new BiFunction<ArrayList<String>, String, ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(ArrayList<String> strings, String s) {
                strings.add(s);
                return strings;
            }
        }, new BinaryOperator<ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(ArrayList<String> strings, ArrayList<String> strings2) {
                return null;  //此时返回  strings,string2,null效果一致
            }
        }).forEach(System.out::println);

        //缩写
        //stream.reduce(new ArrayList<String>() , (strings,string)->{strings.add(string);return strings;},(string1,string2)->string1);







        System.out.println("=====================================================================================================================================");






        Stream<String> stream1 = Stream.of("aa", "ab", "c", "ad");
        Predicate<String> predicate = (s) -> s.contains("a");

        stream1.reduce(new ArrayList<>(), new BiFunction<ArrayList<String>, String, ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(ArrayList<String> strings, String s) {
                if(predicate.test(s))
                strings.add(s);
                return strings;
            }
        }, new BinaryOperator<ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(ArrayList<String> strings, ArrayList<String> strings2) {
                return strings;
            }
        }).forEach(System.out::println);




        System.out.println("=====================================================================================================================================");







        Stream<String> s1 = Stream.of("aa", "ab", "c", "ad");

        //模拟Filter查找其中含有字母a的所有元素，由于使用了r1.addAll(r2)，其打印结果将不会是预期的aa ab ad
        Predicate<String> predicate1 = t -> t.contains("a");
        s1.parallel().reduce(new ArrayList<String>(), (r, t) -> {if (predicate1.test(t)) r.add(t);  return r; },
                (r1, r2) -> {r1.addAll(r2); return r1; }).stream().forEach(System.out::println);

    }

}
