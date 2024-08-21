package _framework.spring.cycle_reference;

import org.aspectj.lang.JoinPoint;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/4/16
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class TrackOperation {
    public void myadvice(JoinPoint jp)//it is advice
    {
        System.out.println("additional concern");
        //System.out.println("Method Signature: "  + jp.getSignature());
    }
}
