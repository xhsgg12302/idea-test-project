package base.hashmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-13
 * @Desc:
 */
public class Test {

    private static final Object[]  a = {};

    private static final String[] b = {"abc","def"};

    public static void main(String[] args) {

        List<String> srcList = new ArrayList<>();
        srcList.add("张三");
        srcList.add("李四");
        srcList.add("王五");

        List<String> descList = new ArrayList<>(srcList);

        Collections.copy(descList, srcList);

        for (String desc : descList) {
            System.out.println(desc);
        }

    }

}
