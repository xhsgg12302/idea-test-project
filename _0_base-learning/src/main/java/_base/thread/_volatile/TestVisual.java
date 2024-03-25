package _base.thread._volatile;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/3/3
 *                          @since  1.0
 *                          @author 12302
 * 
 */
class TestVisual {

    public static class InnerClass {
        public volatile int num = 0;
        public void numTo60() { num = 60; }
    }

    public static void main(String[] args) {
        InnerClass object = new InnerClass();
        new Thread(() -> {
            try {
                Thread.sleep(3 * 1000L);
                object.numTo60();
                System.out.println(Thread.currentThread().getName() + ": updated");
            } catch (InterruptedException e) { throw new RuntimeException(e);}
        }).start();

        while(object.num == 0){ /* infinite loop */}
        System.out.println(Thread.currentThread().getName() + ": skip");
    }
}