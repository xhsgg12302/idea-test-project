package _base.reference;

import java.lang.ref.SoftReference;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/6/30
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class DemoSoftReference {

    public static void main(String[] args) {
        softReference();
    }

    public static void softReference(){
        Object obj = new Object();
        System.out.println(obj);
        SoftReference<Object> soft = new SoftReference<>(obj);
        obj = null;
        System.out.println(soft.get());
        System.gc();
        System.out.println(soft.get());
    }
}
