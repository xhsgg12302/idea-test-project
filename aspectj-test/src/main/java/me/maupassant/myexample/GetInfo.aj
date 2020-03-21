package me.maupassant.myexample;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-01-10
 * @Desc:
 */
public aspect GetInfo {

    pointcut methodCut() : execution(* me.maupassant.myexample.BaseDao+.*(..));

    Object around() : methodCut() {
        System.out.println(thisJoinPointStaticPart.getSignature().getName());
        System.out.println(thisJoinPointStaticPart.getSignature().getDeclaringType().getName());
        printParameters(thisJoinPoint);
        System.out.println("zhegeduixiang" + thisJoinPoint.getThis());
        Object result = null;
        try{
            result = proceed();

            System.out.println("调用结束");
        }catch (Throwable e){
            //Ignore...
        }
        return result;
    }


    static final void println(String s){ System.out.println(s); }
    static private void printParameters(JoinPoint jp) {
        println("Arguments: " );
        Object[] args = jp.getArgs();
        String[] names = ((CodeSignature)jp.getSignature()).getParameterNames();
        Class[] types = ((CodeSignature)jp.getSignature()).getParameterTypes();
        for (int i = 0; i < args.length; i++) {
            println("  "  + i + ". " + names[i] +
                    " : " +            types[i].getName() +
                    " = " +            args[i]);
        }
    }
}
