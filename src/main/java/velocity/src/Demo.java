package velocity.src;

import mybatis.bean.Student;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Author:12302
 * @Date:
 * @Desc:
 */
public class Demo {
    public static void main(String[] args) {
        // 初始化（1）
        Velocity.init("E:/project/MyTest/src/main/resources/velocity.properties");

        // 创建context，存放变量（2）
        VelocityContext context = new VelocityContext();

        context.put("date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //context.put("student", student);

        // 加载模板文件到内存（3）
        Template template = null;
        String templateFile = "demo.vm";
        template = Velocity.getTemplate(templateFile);

        // 渲染（4）
        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);

        // 打印结果
        System.out.println(stringWriter.toString());
    }
}
