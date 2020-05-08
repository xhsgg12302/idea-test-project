package _draft.draft;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-08
 * @Desc:
 */


/*
 * _12302_2020-02-08
 * 阅读以下代码，在如下方法中，存在着大量丑陋的重复代码，请使用“封装”消除重复代码，注意考虑多种异常情况。
 */
public class Test {


    public static void TestMethod() {

        List<String> ret = new ArrayList<String>();

        String str1 = "abc,cd,aa,bb";

        String[] str1Arr = str1.split(",");

        for (String s : str1Arr) {

            if (!s.equals("abc")) {

                ret.add(s);

            }

        }



        String str2 = "123;456;8;9;10;15";

        String[] str2Arr = str2.split(";");

        for (String s : str2Arr) {

            if (!s.equals("123") && !s.equals("8")) {

                ret.add(s);

            }

        }



        String str3 = "192.168.1.1";

        String[] str3Arr = str3.split("\\.");

        for (String s : str3Arr) {

            ret.add(s);

        }

    }

    public static List<String> getList(String origin, String regex, Predicate predicate){
        if(origin == null || "".equals(origin.trim())
        || regex == null || "".equals(regex.trim())) return null;
        List ret = new ArrayList<String>();
        String[] str3Arr = origin.split(regex);
        for (String s : str3Arr) {
            if(predicate.test(s))ret.add(s);
        }
        return ret;
    }

    public static void main(String[] args) {
        String str1 = "abc,cd,aa,bb" ,regex = ",";

        String str2 = "123;456;8;9;10;15" ,regex2 = ";";

        String str3 = "192.168.1.1" ,regex3 ="\\.";

        System.out.println(getList(str1,regex,(s)->!s.equals("abc")));

        System.out.println(getList(str2,regex2,(s)->!s.equals("123") && !s.equals("8")));

        System.out.println(getList(str3,regex3,(s)-> true));

    }
}
