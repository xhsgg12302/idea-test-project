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
        int a = 11;
        // Watches  中添添加变量重新赋值就可以
        Map<String,Object> map = new HashMap<>();
        map.put("name","Pi");
        map.put("age",20);
        System.out.println("a={},map={}" + a + map);
    }
}
