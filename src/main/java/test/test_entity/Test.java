package test.test_entity;

import entity.TestBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-27
 * @Desc:
 */
public class Test {

    public static void main(String[] args) {
        TestBean testBean = new TestBean(){{
            System.out.println("hello inner ");
        }};
        Map map = new HashMap(){{put("code",123);}};
        System.out.println(testBean);
        System.out.println();
    }
}
