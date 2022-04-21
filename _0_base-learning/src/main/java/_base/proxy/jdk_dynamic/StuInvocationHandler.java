package _base.proxy.jdk_dynamic;

import _utils.utils.MonitorUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author stevenobelia
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/3/24 23:55
 * @Description:
 */
public class StuInvocationHandler implements InvocationHandler {

    Object target;

    public StuInvocationHandler(Object target) { this.target = target;}

    /**
     * _base.proxy:代表动态代理对象
     * method：代表正在执行的方法
     * args：代表调用目标方法时传入的实参
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行" +method.getName() + "方法");

        //代理过程中插入监测方法,计算该方法耗时
        MonitorUtil.start();
        Object result = method.invoke(target, args);
        MonitorUtil.finish(method.getName());
        return result;
    }

    public static void main(String[] args) throws Exception{
        Constructor<StuInvocationHandler> constructor = StuInvocationHandler.class.getConstructor(Object.class);
        System.out.println(constructor);
    }
}
