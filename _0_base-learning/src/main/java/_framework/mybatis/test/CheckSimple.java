package _framework.mybatis.test;

import _framework.mybatis.bean.Student;
import _framework.mybatis.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Author:12302
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/7/7 20:16
 * @Description:
 */
public class CheckSimple {
    public static void main(String[] args) {
        InputStream inputStream = null;
        SqlSessionFactory sqlSessionFactory;
        try {
            inputStream = Resources.getResourceAsStream("_framework/mybatis/config/sqlMappersConfig.xml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
         * 创建工厂
         */
        sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        /*
         * 打开ssion
         */
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);
        StudentMapper userMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = userMapper.selectByName("王");

        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }
}
