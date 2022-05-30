package _jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/5/30
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class GcDemo {
    public static void main(String[] args) {

        //  Xm开头的参数也会影响新老比例。
        // -Xmn 新
        // -Xms xiao
        // -Xmx da

        /* _12302_2022/5/30_<
            -Xmn5M
            -Xms5M
            -Xmx5M


            -XX:+UseParallelGC
            -XX:-UseParallelOldGC
            -XX:+UseSerialGC
            -XX:+UseParNewGC


            -XX:+PrintGCDetails
            -XX:+PrintCommandLineFlags
         > */
        List<byte[]> heap = new ArrayList<>();

        int count = 4 * 1024;

        for (int i = 0; i < count; i++) {
            heap.add(new byte[1024]);
        }
        System.out.println("finished");
    }
}
