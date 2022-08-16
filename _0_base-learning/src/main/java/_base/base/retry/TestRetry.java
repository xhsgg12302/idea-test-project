package _base.base.retry;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2022/7/11
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class TestRetry {
    public static void testRetry() {
        retry: for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(i + "-" + j);
                if (j == 3) {
                    // break retry;
                    continue retry;
                }
            }
        }
        System.out.println("==>end");
    }

    public static void main(String[] args) {
        testRetry();
    }
}