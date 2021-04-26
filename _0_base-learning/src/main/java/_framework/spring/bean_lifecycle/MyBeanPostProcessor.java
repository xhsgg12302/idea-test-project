package _framework.spring.bean_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor  implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Book)
            System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization invoke");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Book)
            System.out.println("MyBeanPostProcessor.postProcessAfterInitialization invoke");
        return bean;
    }
}
