package _framework.javassist.modify_system_classs;


import java.lang.String;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.Modifier;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/11/23
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Product {

    public static void main(String[] args) {
        try{
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("java.lang.String");
            CtField f = new CtField(CtClass.intType, "hiddenValue", cc);
            f.setModifiers(Modifier.PUBLIC);
            cc.addField(f);
            cc.writeFile("D:\\idea-project\\idea-test-project\\_0_base-learning\\target\\classes\\_framework\\javassist");
        }catch (Exception e){ /* _12302_2021/11/23_< ignore... > */}
    }
    
}
