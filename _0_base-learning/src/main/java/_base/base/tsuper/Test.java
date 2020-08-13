package _base.base.tsuper;

import javax.servlet.http.HttpServlet;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-06
 * @Desc:
 */
public class Test {
    public Test(){
        System.out.println("haha");
    }
    public void test(){
        System.out.println("_draft/test");
    }

    public static void main(String[] args) {
        C c = new C();
        c.test();
    }
}

class A extends Test{
    public A(){
        Object  a =  new Object();
        System.out.println("A");
    }
    public A(String a){

        System.out.println("A(a)");
    }
    @Override
    public void test() {
        //也就是说如果重写了的话，就不会隐式调用super._draft.test() 方法
        //而构造器不写的话会隐式调用super（），写的话就是显示调用super（）；
        System.out.println("可不可以去除父类 test方法");
    }
}

abstract class B extends A{
    //如果B 没有重写的话，默认继承A的，应该输出A
    public B(){
        //super();
        super("a");
    }

    /*@Override
    public void _draft.test() {
        //也就是说如果重写了的话，就不会隐式调用super._draft.test() 方法
        //而构造器不写的话会隐式调用super（），写的话就是显示调用super（）；
        System.out.println("可不可以去除父类 test方法");
    }*/
}

class C extends B{
    public C(){
        super();//放错位置会编译错误
        System.out.println("构造方法");


    }
    @Override
    public void test(){
        System.out.println("hello super");
        super.test();


        Long i = 10l;
        //先运算，后转换
        //(String)i.intValue();
    }
}

class BbsServlet extends HttpServlet{

}
