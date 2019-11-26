package base.tsuper;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-06
 * @Desc:
 */
public class Test {
    public void test(){
        System.out.println("test");
    }

    public static void main(String[] args) {
        C c = new C();
        c.test();
    }
}

class A extends Test{

}

abstract class B extends A{
    //如果B 没有重写的话，默认继承A的，应该输出A
    public B(){
        System.out.println("父类方法");
    }
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
    }
}
