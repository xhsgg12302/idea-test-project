package _framework.spring.factory_bean;

import _framework.spring.factory_bean.beans.Tool;
import _framework.spring.factory_bean.beans.ToolFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;

public class SpringFactoryBeanTest {

    public static void main(String[] args) {

        // 获取xml文件的一种方式。
        URL config = SpringFactoryBeanTest.class.getResource("../res/factory-bean.xml");

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("_framework/spring/res/factory-bean.xml");
        ToolFactory fb = applicationContext.getBean("&toolFB", ToolFactory.class);
        Tool tool1 = applicationContext.getBean(Tool.class);
        Tool tool2 = applicationContext.getBean(Tool.class);
        System.out.println();
        applicationContext.destroy();
    }

}
