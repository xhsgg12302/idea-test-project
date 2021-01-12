package me.maupassant.springmvc.test;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
public class Person {

    public String name;
    public Integer age;

    //基本数据类型
    public Boolean flag;
    public int index;
    public List<String> list;
    public Map<String,String> map;
}
