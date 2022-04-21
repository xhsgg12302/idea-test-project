package site.wtfu.framework.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import site.wtfu.framework.config.BeanMyScope;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Spring Context 工具类
 * 
 * @author lyf
 * 
 * @date 2016年11月29日 下午11:45:51
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	public static ApplicationContext applicationContext;



	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}
	
	public static Collection<Object> getBeanByAnnotation(Class<? extends Annotation> c) {
		
		Map<String, Object> map = applicationContext.getBeansWithAnnotation(c);
		
		
		
		return  new ArrayList<>(map.values());
		
	}
	
	
	public static <T> Collection<T> getBeanByType(Class<T> c) {
		
		Map<String, T> map = applicationContext.getBeansOfType(c);
		 
		return  new ArrayList<T>(map.values());
		
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	
	public static <T> T getBean( Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public static boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}

	public static Class<? extends Object> getType(String name) {
		return applicationContext.getType(name);
	}

}