package _base.thread.threadpool;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/19
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class TestRetry {

    public static void main(String[] args) {
        test01();
    }

    public static void test01(){
        System.out.println("start");

        retry:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(j < 10)
                    System.out.println(i + " - " + j + ": times");

                if(j == 5)
                    break retry;

                if(j == 6)
                    continue retry;
            }
        }

        System.out.println("end");
    }
}
