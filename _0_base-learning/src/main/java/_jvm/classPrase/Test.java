package _jvm.classPrase;

/**
 * Copyright 2022 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2022-05-27
 */
public class Test {

    private void foo() {
        {
            int a = 1;
            int b = 2;
        }
        {
            float c = 3f;
        }
        {
            long d = 4;
        }
    }
}
