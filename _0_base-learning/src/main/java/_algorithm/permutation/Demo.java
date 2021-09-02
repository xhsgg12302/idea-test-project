package _algorithm.permutation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright 2021 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2021-08-26
 */
public class Demo {

    @Test
    public void test(){
        String[] elements = new String[]{"正","负"};
        //permutation(elements, 3).stream().forEach(System.out::println);
        increments(elements, 4).stream().forEach(System.out::println);
    }

    public List<String> permutation(String[] recursion, int length){
        if(1 == length){
            return Arrays.asList(recursion);
        }else{
            List<String> result = new ArrayList<>();
            for (String each : recursion) {
                for (String retn : permutation(recursion, length - 1)) {
                    result.add(each + retn);
                }
            }
            return result;
        }
    }

    public List<String> increments(String[] recursion, int length){
        List<String> result = new ArrayList(){{ add("");}};
        int i = 1;
        while(i++ <= length){
            List<String> temp = new ArrayList<>();
            for (String s : recursion) {
                for (String s1 : result) {
                    temp.add(s + s1);
                }
            }
            result = temp;
        }
        return result;
    }

}
