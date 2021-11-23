package _framework.javassist;

import javassist.ClassPool;
import javassist.CtClass;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/11/19
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Demo {

    public static void main(String[] args) {
        createClass();
    }
    
    
    public static void createClass() {
        try{
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("_framework.javassist.helper.Origin");
            cc.setSuperclass(pool.get("_framework.javassist.helper.Base"));

            cc.writeFile("D:\\idea-project\\idea-test-project\\_0_base-learning\\src\\main\\java\\_framework\\javassist");
            Object o = cc.toClass().newInstance();
            System.out.println("pause");
        }catch (Exception e) { e.printStackTrace();}
    }
}
