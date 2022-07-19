package _base.thread.interrupt;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/7/11
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class EndThread {
    private static class UseThread extends Thread {

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.err.println(threadName + " interrupt flag before=" + isInterrupted());
            long i = 0;
            //while(true){

            // isInterrupted() 方法会在 interrupt（）调用之后 返回true，然后不会充值 标志位
            // interrupted() 方法会在 interrupt（）调用之后， 返回true, 但是会清除标志位。

            while (!isInterrupted()) {
            //while (!Thread.interrupted()) {

                i += 1;


            }
            System.out.println(i);
            System.err.println(threadName + " interrupt flag after =" + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(1000);
        endThread.interrupt();//中断线程，其实设置线程的标识位true
    }
}
