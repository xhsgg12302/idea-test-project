package _base.base.localDateTime.test;

import _base.base.localDateTime.entity.Entity;
import com.alibaba.fastjson.JSON;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-31
 * @Desc:
 */
public class Test {
    public static void main(String[] args) {
        Entity entity = new Entity();
        entity.setId(1l);
        entity.setName("wang");
        entity.setStartTime(LocalDateTime.of(2019,10,21,13,23,24));
        entity.setEndTime(LocalDateTime.of(2019,10,22,13,23,24));
        entity.setRemark("hello world");


        System.out.println(JSON.toJSONString(entity));


        Map<String,Object> entityMap = new HashMap<>();
        entityMap.put("id",1l);
        entityMap.put("name","wang");
        entityMap.put("startTime",LocalDateTime.of(2019,10,21,13,23,24));
        entityMap.put("endTime",LocalDateTime.of(2019,10,22,13,23,24));
        entityMap.put("remark","hello world");

        String abc = JSON.toJSONString(entityMap);
        System.out.println(abc);


        Entity temp = JSON.parseObject(abc,Entity.class);
        Map<String,Object> tempMap = JSON.parseObject(abc,Map.class);

        System.out.println();

    }
}
