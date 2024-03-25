package site.wtfu.framework.web.test;

import site.wtfu.framework.entity.Dept;

import java.beans.PropertyEditorSupport;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/24
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class CustomDeptEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(text.indexOf(",") > 0) {
            Dept dept = new Dept();
            String[] arr = text.split(",");
            dept.setId(Integer.parseInt(arr[0]));
            dept.setName(arr[1]);
            setValue(dept);
        } else {
            throw new IllegalArgumentException("dept param is error");
        }
    }

}
