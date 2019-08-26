package io.byteArrayOutputDir;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Author:12302
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/7/6 17:39
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        bops.write(102);
        System.out.println(Arrays.toString(bops.toByteArray()));
    }
}
