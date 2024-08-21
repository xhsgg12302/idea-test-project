package _framework.spring.cycle_reference;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/4/16
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class Operation {
    // Method 1
    public void msg()
    {
        System.out.println("msg() method invoked");
    }
    // Method 2
    public int m()
    {
        System.out.println("m() method invoked");
        return 2;
    }
    // Method 3
    public int k()
    {
        System.out.println("k() method invoked");
        return 3;
    }
}
