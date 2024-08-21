package _base.proxy.cglib_dynamic;

import _utils.utils.MonitorUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/4/8
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class Main {

    public static void main(String[] args) {
        //System.setProperty("cglib.debugLocation",System.getProperty("user.dir"));

        MethodInterceptor interceptor = new MethodInterceptor() {
            public Object target;
            {this.target = new StudentNoIntfs("eli _w");}
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("intercept: " + method.getName());
                MonitorUtil.start();
                Object result = method.invoke(target, args);
                MonitorUtil.finish(method.getName());
                return result;
            }
        };

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(StudentNoIntfs.class);
        enhancer.setCallback(interceptor);
        StudentNoIntfs proxy = (StudentNoIntfs) enhancer.create();
        proxy.sayHello("exec: hello cglib");
    }
}
