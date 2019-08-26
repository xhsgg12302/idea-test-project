package classLoader;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/3/18 15:18
 * @Description:
 */

/**
 * 父类
 * <p>
 * Created by lxk on 2017/4/20
 */
public class Parent {
    static {
        System.out.println("父类：静态代码块");
    }

    private static String staticStringInParent = initStaticStringInParent();

    private String stringInParent = initStringInParent();

    public Parent() {
        System.out.println("父类：构造方法");
    }

    {
        System.out.println("父类：普通代码块");
    }

    private static String initStaticStringInParent() {
        System.out.println("父类：静态方法，被静态成员变量赋值调用。");
        return "initStaticStringInParent";
    }
    private String initStringInParent() {
        System.out.println("父类：普通成员方法，被普通成员变量赋值调用。");
        return "initStringInParent";
    }

    public static void main(String[] args) {
        Parent p = new Child();

        System.out.println("\n===============================\n\n\n");
        p = new Parent();
    }

}


class Child extends Parent {

    {
        System.out.println("子类：普通代码块");
    }

    static {
        System.out.println("子类：静态代码块");
    }
    private String stringInChild = initStringInChild();

    private static String staticStringInChild = initStaticStringInChild();

    public Child() {
        System.out.println("子类：构造方法");
    }

    private static String initStaticStringInChild() {
        System.out.println("子类：静态方法，被静态成员变量赋值调用。");
        return "initStaticStringInChild";
    }

    private String initStringInChild() {
        System.out.println("子类：普通成员方法，被普通成员变量赋值调用。");
        return "initStringInChild";
    }
}


