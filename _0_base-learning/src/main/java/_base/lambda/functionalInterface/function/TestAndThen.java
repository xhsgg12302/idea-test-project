package _base.lambda.functionalInterface.function;

import java.util.function.Function;

/**
 * Copyright 2021 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2021-04-30
 */
public class TestAndThen {


    public static void main(String[] args) {

        Function<Integer,Integer> first = x -> x * x;
        new Function<Integer,Integer>(){
            @Override
            public Integer apply(Integer x) {
                return  x * x;
            }
        }.andThen(new Function<Integer,Integer>(){
            public Integer apply(Integer y) {
                return y * 2;
            }
        });



        Function<Integer,Integer> second = y -> y * 2;


        //(T t) -> after.apply(apply(t));
        //return new Function<Object,Test>(){

            //@Override
            //public Test apply(Object o) {
                //return after.apply(apply(t));
            //}
        //};

        first.andThen(second).apply(2);


    }
}
