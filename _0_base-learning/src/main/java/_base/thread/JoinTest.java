package _base.thread;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/6/15 14:41
 * @Description:    线程对象调用.join 方法后， 会将当前线程（main）挂起，
 *                  现在例子现象是：m1,m2同时启动，并将m2加入主线程之前，（此时主线程停下来了，m3也没有启动）
 *                  等到m2完事以后，m1(5-9),m3,主线程同时执行了，
 */
public class JoinTest {
    public static void main(String[] args) throws Exception{
        //MyThread1 m1 = new MyThread1("A");

        //MyThread m2 = new MyThread("B");

        //MyThread m3 = new MyThread("C");

        //m1.start();


        //m2.start();


        //m2.join(4000);


        //m3.start();
        ///m3.join();

        Thread n1 = new Thread(()->{
            try {
                Thread.sleep(1000);
                System.out.println("n1 done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        n1.start();
        n1.join();

        Thread n2 = new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println("n2 done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        n2.start();
        n2.join();

        Thread n3 = new Thread(()->{
            try {
                Thread.sleep(3000);
                System.out.println("n3 done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        n3.start();
        //n3.join();



        System.out.println("main is dead");
    }

}

class MyThread extends Thread{
    public MyThread(String name){
        super(name);
    }
    @Override
    public void run(){
        for(int i = 0;i<100;i++){
            /*for(int j =0;j<=1000;j++){
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t"+j);
            }*/
            System.out.println(Thread.currentThread().getName() + "\t" + i);
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
class MyThread1 extends Thread{
    public MyThread1(String name){
        super(name);
    }
    @Override
    public void run(){
        for(int i = 0;i<200;i++){
            /*for(int j =0;j<=1000;j++){
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t"+j);
            }*/
            System.out.println(Thread.currentThread().getName() + "\t" + i );
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
