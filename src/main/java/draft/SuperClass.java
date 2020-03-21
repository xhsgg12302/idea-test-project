package draft;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-10
 * @Desc:
 */
public class SuperClass {

    public static void main(String[] args) {

        System.out.println(A.class.getSuperclass());
        System.out.println(B.class.getSuperclass());
        System.out.println(C.class.getSuperclass());

    }
}

class A{}
class B extends A{}
class C extends B{}
