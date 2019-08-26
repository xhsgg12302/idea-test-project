package thread;

import java.util.Timer;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/3/5 11:29
 * @Description:
 */
public class daemonTest {
    public static void main(String[] args) {
        A a = new A();
        Thread at = new Thread(a,"a_thread");
        at.setDaemon(true);
        at.start();
        Thread bt = new Thread(new B(),"b_thread");
        bt.start();
        for(int i =0;i<5;i++){
            try {
                System.out.println("main_"+i);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("main_thread_has_die");
    }
}
class A implements Runnable{

    @Override
    public void run() {
        for(int i =0;i<100;i++){
            try {
                System.out.println("sub_--------------------------------"+i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class B implements Runnable{

    @Override
    public void run() {
        for(int i =0;i<10;i++){
            try {
                System.out.println("work_"+i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("work_thread_had_die");
    }
}

class C  implements Runnable{
    @Override
    public void run(){
        Timer time = new Timer();
    }
}
