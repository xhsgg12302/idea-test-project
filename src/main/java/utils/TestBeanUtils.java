package utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2018/12/7 17:57
 * @Description:
 */
public class TestBeanUtils {
    public static void main(String [] args){
        Map<String ,Object> map = new HashMap<>();
        map.put("1","你好");
        map.put("2","世界");

        try {
            System.out.println(BeanUtils.getProperty(map,"1"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
