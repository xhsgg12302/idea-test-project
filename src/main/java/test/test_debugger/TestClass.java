package test.test_debugger;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-17
 * @Desc:
 */
public class TestClass {
    public static void main(String args[]){

        //test();
        test1();
        //test2();
    }


    //debugger 修改变量
    public static void test(){
        int a = 11;
        // Watches  中添添加变量重新赋值就可以
        Map<String,Object> map = new HashMap<>();
        map.put("name","Pi");
        map.put("age",20);
        System.out.println("a={},map={}" + a + map);
    }

    //条件断点
    public static void test1(){
        for (int i = 0 ; i < 100 ; i++){
            int temp = i ;
            System.out.println(temp);
        }
    }

    //异常断点
    public static void test2(){

        String i = null;
        System.out.println("132");
        System.out.println("132");
        System.out.println("132");
        System.out.println("132");
        System.out.println("132");
        System.out.println(i.length());
        System.out.println("132");
        System.out.println("132");
        System.out.println("132");
    }
}
