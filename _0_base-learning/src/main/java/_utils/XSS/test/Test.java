package _utils.XSS.test;

import _utils.XSS.filter.XssHttpServletRequestWrapper;

import java.io.UnsupportedEncodingException;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/5/22 15:23
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String abc = "<script>alert('HelloWorld')<script/>";
        byte [] def = abc.getBytes("utf-8");
        String temp = XssHttpServletRequestWrapper.xssEncode(abc);
        byte [] ghk = temp.getBytes("utf-8");


        System.out.println();
    }
}
