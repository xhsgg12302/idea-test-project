package _draft.test;

import _utils.thread.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/6/6
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class StackAlloc {

    /**
     * 栈上分配测试步骤
     *      1，开启GC日志，保证是不GC影响
     *      2，使用HSDB工具，方法在jdk-home/lib/sa-jdi.jar, 启动  sudo java -cp sa-jdi.jar sum.jvm.hotspot.HSDB
     *      3，当开启逃逸分析【-XX:+DoEscapeAnalysis 默认开启】后，查看堆上分配对象的数量
     *      4，关闭后，查看堆上分配对象的数量。
     *
     *
     *  局部变量，如果GC ROOT引用的对象的话，是不会发生栈上分配的。
     * @param args
     */

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        List abc = new ArrayList<StackAlloc>(100000);

        for (int i = 0; i < 100000; i++) {
            alloc(abc);
        }

        long end = System.currentTimeMillis();

        System.out.println(end -start);

        ThreadUtil.keepRunning();
    }

    public static void alloc(List abc){
        StackAlloc stackAlloc = new StackAlloc();
        abc.add(stackAlloc);
    }

}
