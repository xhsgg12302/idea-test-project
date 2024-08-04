package _framework.spring.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.Locale;

/**
 * see: https://blog.csdn.net/u012702547/article/details/134570847
 */
public class SpringI18nTest {

    public static void main(String[] args) {

        // 获取xml文件的一种方式。
        URL config = SpringI18nTest.class.getResource("../res/i18n.xml");

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("_framework/spring/res/i18n.xml");

        MessageSource source = applicationContext.getBean(MessageSource.class);
        Locale localeZh = new Locale("zh", "CN");
        String hello = source.getMessage("hello", null, localeZh);
        System.out.println("hello = " + hello);

        Object[] params = new Object[]{"java","world"};
        String name = source.getMessage("name", params, localeZh);
        System.out.println("name = " + name);
        System.out.println();

        applicationContext.destroy();
    }

}
