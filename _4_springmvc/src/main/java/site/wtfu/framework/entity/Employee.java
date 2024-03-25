package site.wtfu.framework.entity;

import lombok.Data;
import lombok.ToString;


/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-14
 * @Desc:
 */
@Data
@ToString
public class Employee {

    private Integer id;

    private String name;

    private int age;

    private Dept dept;

    public Employee(){};
    public Employee(int age,String name){
        this.age = age;
        this.name = name;
    }
}
