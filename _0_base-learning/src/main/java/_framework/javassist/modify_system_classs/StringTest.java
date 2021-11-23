package _framework.javassist.modify_system_classs;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/11/23
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class StringTest {


    /**
     *  1，product 生成一个java.lang.String.class类
     *  2，写一个测试的类StringTest
     * 
     * 
     *  to run:
     *      
     *          % java -Xbootclasspath/p:. StringTest arg1 arg2...
     *          
     *  really:
     *      cd ../target/classes
     *      
     *      java  -XX:+TraceClassLoading -Xbootclasspath/p:./_framework/javassist  _framework.javassist.modify_system_classs.StringTest
     * 
     * 
     * 
     * 
     * @param args
     * @throws Exception
     */
    
    
    public static void main(String[] args) throws Exception{
        System.out.println(String.class.getField("hiddenValue").getName());
    }
    
}
