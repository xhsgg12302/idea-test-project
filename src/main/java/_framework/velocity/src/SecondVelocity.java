package _framework.velocity.src;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Author:12302
 * @Date:
 * @Desc:
 */
public class SecondVelocity {
    public static void main(String[] args) {

            VelocityEngine ve = getVelocityEngine();
            VelocityContext context = getVelocityContext();
            Template template = ve.getTemplate("second.vm");
            if(template != null){
                StringWriter getWriter = new StringWriter();
                template.merge(context, getWriter);
                System.out.println(getWriter);
            }
    }
    public static VelocityContext getVelocityContext(){
        VelocityContext context = new VelocityContext();
        context.put("list", getNames());
        context.put("listMap", getNamesMap());
        Map<String,String> para = getUserInfo();
        for(String key : para.keySet()){
            context.put(key, para.get(key));
        }
        return context;
    }
    public static VelocityEngine getVelocityEngine(){
        VelocityEngine ve = new VelocityEngine();
        ve.init("E:/project/my-_draft.test/src/main/resources/_framework.velocity.properties");
        return ve;
    }
    public static ArrayList getNames() {
        ArrayList list = new ArrayList();
        list.add("刘少奇");
        list.add("李逵");
        list.add("王熙凤");
        list.add("李四光");
        return list;
    }
    public static ArrayList getNamesMap() {
        ArrayList list = new ArrayList();
        Map map = new HashMap();
        map.put("name", "书包");
        map.put("price", "$100.00");
        list.add( map );
        map = new HashMap();
        map.put("name", "唱片");
        map.put("price", "$59.99");
        list.add( map );
        map = new HashMap();
        map.put("name", "小菜");
        map.put("price", "$3.99");
        list.add( map );
        return list;
    }
    public static Map getUserInfo(){
        Map<String,String> para = new HashMap<String,String>();
        para.put("name", "dirk.zhang");
        para.put("age", "20");
        para.put("sex", "male");
        return para;
    }

}
