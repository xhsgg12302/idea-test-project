package site.wtfu.framework.web.test;


import com.sun.beans.editors.IntegerEditor;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/23
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class TestJavaBeanPropertyEditor {

    public static void main(String[] args) {

        IntegerEditor integerEditor = new IntegerEditor();

        integerEditor.setAsText("12302");

        Object value = integerEditor.getValue();

        System.out.println(value);
    }

}
