package _jvm.classLoader.parent_son_loader_order;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/3/18 15:18
 * @Description:
 */

/**
 *
 * 父类：静态代码块
 * 父类：静态方法，被静态成员变量赋值调用。
 * 子类：静态代码块
 * 子类：静态方法，被静态成员变量赋值调用。
 * 父类：普通成员方法，被普通成员变量赋值调用。
 * 父类：普通代码块
 * 父类：构造方法
 * 子类：普通代码块
 * 子类：普通成员方法，被普通成员变量赋值调用。
 * 子类：构造方法
 *
 *
 */
public class Parent {
    static {
        System.out.println("父类：静态代码块"); //1
    }

    private static String staticStringInParent = initStaticStringInParent();

    private String stringInParent = initStringInParent();

    public Parent() {
        System.out.println("父类：构造方法"); // 7
    }

    {
        System.out.println("父类：普通代码块");  //  6
    }

    private static String initStaticStringInParent() {
        System.out.println("父类：静态方法，被静态成员变量赋值调用。"); //2
        return "initStaticStringInParent";
    }
    private String initStringInParent() {
        System.out.println("父类：普通成员方法，被普通成员变量赋值调用。"); // 5
        return "initStringInParent";
    }

    public static void main(String[] args) {
        Parent p = new Child();

        //System.out.println("\n===============================\n\n\n");
        //p = new Parent();
    }

}


class Child extends Parent {

    {
        System.out.println("子类：普通代码块");  // 8
    }

    static {
        System.out.println("子类：静态代码块");     //3
    }
    private String stringInChild = initStringInChild();

    private static String staticStringInChild = initStaticStringInChild();

    public Child() {
        System.out.println("子类：构造方法"); //10
    }

    private static String initStaticStringInChild() {
        System.out.println("子类：静态方法，被静态成员变量赋值调用。"); //4
        return "initStaticStringInChild";
    }

    private String initStringInChild() {
        System.out.println("子类：普通成员方法，被普通成员变量赋值调用。"); // 9
        return "initStringInChild";
    }
}


