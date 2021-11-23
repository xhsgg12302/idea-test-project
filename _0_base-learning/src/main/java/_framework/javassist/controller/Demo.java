package _framework.javassist.controller;

import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

import java.io.IOException;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/11/23
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Demo {

    public static void main(String[] args) {
        try {
            makeClass("controller.DemoController","sayHello","showFiled","interfaceCode");
        } catch (Exception e) { e.printStackTrace(); }
    }



    public static void makeClass(String className,String methodName, String fieldName,String interfaceCode) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.makeClass(className);
        
        ClassFile ccFile = clazz.getClassFile();
        ConstPool constpool = ccFile.getConstPool();

        CtClass service = pool.get("_framework.javassist.controller.service.ITestService");
        CtClass request = pool.get("javax.servlet.http.HttpServletRequest");
        CtClass response = pool.get("javax.servlet.http.HttpServletResponse");

        
        // 增加字段
        CtField field = new CtField(service,fieldName,clazz);
        field.setModifiers(Modifier.PUBLIC);
        FieldInfo fieldInfo = field.getFieldInfo();

        // 属性附上注解
        AnnotationsAttribute fieldAttr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
        Annotation autowired = new Annotation("org.springframework.beans.factory.annotation.Autowired",constpool);
        fieldAttr.addAnnotation(autowired);
        fieldInfo.addAttribute(fieldAttr);
        clazz.addField(field);

        // 增加方法，javassist可以直接将字符串set到方法体中，所以使用时非常方便
        CtMethod method = new CtMethod(pool.get("java.lang.Object"),methodName,new CtClass[]{request,response},clazz);
        method.setModifiers(java.lang.reflect.Modifier.PUBLIC);
        StringBuffer methodBody = new StringBuffer();
        methodBody.append("{return "+fieldName+".execute(\""+interfaceCode+"\",$1.getAttribute(\"request\"));}");
        method.setBody(methodBody.toString());



        // 类附上注解
        AnnotationsAttribute classAttr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
        Annotation controller = new Annotation("org.springframework.stereotype.Controller",constpool);
        Annotation requestMapping = new Annotation("org.springframework.web.bind.annotation.RequestMapping.RequestMapping",constpool);
        String visitPath = "/api/department";
        requestMapping.addMemberValue("value",new StringMemberValue(visitPath,constpool));
        classAttr.addAnnotation(controller);
        classAttr.addAnnotation(requestMapping);
        ccFile.addAttribute(classAttr);

        //方法附上注解
        AnnotationsAttribute methodAttr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
        //Annotation annotation3 = new Annotation("org.springframework.web.bind.annotation.RequestMapping.RequestMapping",constpool);
        requestMapping.addMemberValue("value",new StringMemberValue("/register",constpool));

        Annotation responseBody = new Annotation("org.springframework.web.bind.annotation.RequestMapping.ResponseBody",constpool);
        methodAttr.addAnnotation(requestMapping);
        methodAttr.addAnnotation(responseBody);
        MethodInfo info = method.getMethodInfo();
        info.addAttribute(methodAttr);
        clazz.addMethod(method);
        clazz.writeFile("D:\\idea-project\\idea-test-project\\_0_base-learning\\src\\main\\java\\_framework\\javassist");

    }
}
