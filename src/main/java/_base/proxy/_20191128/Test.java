package _base.proxy._20191128;

import _base.proxy.Person;
import _base.proxy.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-28
 * @Desc:
 */
public class Test implements InvocationHandler {

    private Object target;

    public Test(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //需要代理处理的方法
        System.out.println(proxy.getClass().getName() + "  _base.proxy handle something");

        return method.invoke(target,args);
    }

    public static void main(String [] args){
        Person real = new Student("elizabeth");

        Test test = new Test(real);

        Person proxyInstance = (Person)Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{Person.class}, test);

        proxyInstance.sayHello("everyone");
    }
}
