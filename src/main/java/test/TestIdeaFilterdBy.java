package test;

import java.util.LinkedHashMap;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-07
 * @Desc:
 */
public class TestIdeaFilterdBy {
    public static void main(String[] args) {
        String [] strs = new String[]{"hello","world","tomorrow","belong","us"};

        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("张三",123);
        map.put("里斯",96);
        map.put("王五",52);
        map.put("马六",34);
        map.put("痞气",12);


        //this.contains("l")
        System.out.println(strs);

        //((String)(this.key)).contains("张三")
        System.out.println(map);

    }
}
