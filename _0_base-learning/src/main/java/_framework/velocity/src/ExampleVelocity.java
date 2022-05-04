package _framework.velocity.src;

import _framework.mybatis.bean.Student;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.StringWriter;
import java.net.URL;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Author:12302
 * @Date:
 * @Desc:
 */
public class ExampleVelocity {


    @Test
    public void test(){
        try {
            URL resource = this.getClass().getClassLoader().getResource("_framework/velocity/res/velocity.properties");
            // 初始化（1）
            Velocity.init(resource.getFile());

            // 创建context，存放变量（2）
            VelocityContext context = new VelocityContext();
            Student student = new Student();
            student.setName("elizabeth");
            context.put("student", student);

            // 加载模板文件到内存（3）
            Template template = null;
            String templateFile = "example.vm";
            template = Velocity.getTemplate(templateFile);

            // 渲染（4）
            StringWriter stringWriter = new StringWriter();
            template.merge(context, stringWriter);

            // 打印结果
            System.out.println(stringWriter.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
