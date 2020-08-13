package _base.proxy;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/3/25 16:48
 * @Description:
 */
import sun.reflect.Reflection;

public class Test
{
    public static void main(String[] args)
    {
        Test2 test=new Test2();
        test.g();
    }
}


class Test2
{
    public  void g(){
        gg();
    }
    public  void gg(){
        System.out.println("-1 : "+Reflection.getCallerClass(-1));
        System.out.println("0 : "+Reflection.getCallerClass(0));
        System.out.println("1 : "+Reflection.getCallerClass(1));
        System.out.println("2 : "+Reflection.getCallerClass(2));
        System.out.println("3 : "+Reflection.getCallerClass(3));
        System.out.println("4 : "+Reflection.getCallerClass(4));
        System.out.println("5 : "+Reflection.getCallerClass(5));
    }

}
