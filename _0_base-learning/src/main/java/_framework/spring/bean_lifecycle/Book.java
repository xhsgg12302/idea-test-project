package _framework.spring.bean_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * console output: doCreateBean()
 *
 * createBeanInstance                               --> Book initializing
 *
 * populateBean(beanName, mbd, instanceWrapper);    --> setBookName: Book name has set.
 *
 * initializeBean           invokeAwareMethods      --> BeanNameAware.setBeanName() invoke
 *                                                  --> BeanFactoryAware.setBeanFactory() invoke
 *
 *                          applyBeanPostProcessorsBeforeInitialization
 *                          (BeanPostProcess)       --> ApplicationContextAware.setApplicationContext() invoke
 *                                                  --> MyBeanPostProcessor.postProcessBeforeInitialization invoke
 *                                                  --> @PostConstruct
 *
 *                          invokeInitMethods       initializingBean                   --> initializingBean.afterPropertiesSet() invoke
 *                                                  invokeCustomInitMethod             --> define constructor invoke
 *
 *                          applyBeanPostProcessorsAfterInitialization
 *                          (BeanPostProcess)       --> MyBeanPostProcessor.postProcessAfterInitialization invoke
 *
 *
 * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * using                                                  --> Book name is thinking in java
 *                                                  --> [org.springframework.context.support.ClassPathXmlApplicationContext] - Closing org.springframework.context.support.ClassPathXmlApplicationContext@277c0f21: startup date [Sun Apr 11 20:36:15 CST 2021]; root of context hierarchy
 *
 *
 * destroy                                          --> @PreDestroy
 *                                                  --> DisposableBean.destroy() invoke
 *                                                  --> define destroy invoke
 *
 */
public class Book implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String bookName;
    public Book(){
        System.out.println("Book initializing");
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        System.out.println("setBookName: Book name has set.");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("object's finalize invoke");
    }

    ////////////////////////
    // interfaces implements
    ////////////////////////
    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware.setBeanName() invoke");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware.setBeanFactory() invoke");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware.setApplicationContext() invoke");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("initializingBean.afterPropertiesSet() invoke");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean.destroy() invoke");
    }

    ///////////////////////////
    // define myself constructor
    ///////////////////////////
    public void myPostConstruct(){
        System.out.println("define constructor invoke");
    }

    @PostConstruct
    public void sprintPostConstruct(){
        System.out.println("@PostConstruct");
    }

    public void myPreDestroy(){
        System.out.println("define destroy invoke");
    }

    @PreDestroy
    public void springPreDestory(){
        System.out.println("@PreDestroy");
    }

}

