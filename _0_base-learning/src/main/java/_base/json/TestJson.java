package _base.json;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import _draft.entity.TestBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/3/22 16:45
 * @Description:
 */
public class TestJson {
    public static void main(String[] args) {
        List<TestBean> list = new ArrayList<>();
        TestBean en = new TestBean();
        en.setdChanel("abc");
        en.setdIncome(new BigDecimal(20.01));


        //json字符串是自动匹配入值，属性多少都没关系。
        String jsonstr = "{\"abc\":123}";

        //List<_draft.test.TestBean> tb = JSONObject.parseArray(jsonstr,_draft.test.TestBean.class);
        TestBean main = JSON.parseObject(jsonstr,TestBean.class);


        list.add(en);
        en = new TestBean();
        en.setdChanel("efg");
        en.setdIncome(new BigDecimal(12.34));
        en.setdType("type");
        list.add(en);

        System.out.println(JSON.toJSONString(list));

        List<TestBean> abc = JSONObject.parseArray(JSON.toJSONString(list),TestBean.class);

        Map<String,Object> map = new LinkedHashMap<>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);
        System.out.println(JSON.toJSONString(map));
    }
}
