package _draft.test;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/3/6 16:50
 * @Description:
 */
public class Utilstest {
    public static List<Integer> getSeason(int month){
        List<Integer> result = new ArrayList<>();
        int [] first = {1,2,3};
        for(int i =0 ; i < first.length ;i++){
            if(month == first[i]){
                for(int f :first){result.add(f);}
                break;
            }
            if(i == first.length-1){
                int [] temp = new int[first.length];
                for(int z=0;z<first.length;z++){
                    temp[z] = first[z]+3;
                }
                first = temp;i=-1;
            }
        }
        return result;
    }


    public static void show(List<T> list){
        for(Object object : list){
            System.out.println(object.toString());
        }
    }
    public static void show1(List<?> list){

    }

    public static void main(String[] args) {

    }
}
