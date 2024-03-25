package _base.thread.test;

import org.junit.Test;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/3/1
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class TestThreadLocal {

    @Test
    public void test01(){
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("hello world");
    }
}
