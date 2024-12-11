package _framework.mybatis.mapper;

import _framework.mybatis.bean.Student;

import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Author:12302
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/7/7 20:07
 * @Description:
 */
public interface StudentMapper {

    Student selectByName(String name);

    List<Student> selectAll();
}
