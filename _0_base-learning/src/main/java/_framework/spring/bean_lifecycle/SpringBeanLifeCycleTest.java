package _framework.spring.bean_lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanLifeCycleTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Bean-Lifecycle.xml");
        Book book = (Book)applicationContext.getBean("book");
        System.out.println("Book name is " + book.getBookName());
        ((ClassPathXmlApplicationContext) applicationContext).destroy();
    }

}
