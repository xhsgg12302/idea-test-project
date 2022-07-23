package _base.thread._synchronized;

/**
 *
 * 加synchronized的方法，线程会读锁，
 * 而没有加的线程不会读锁，直接进入执行
 *
 * 20220722 add， synchronized 在方法上锁住的是对象。即使同一个对戏那个不同syn方法，也会出现同步的情况。
 *                对象的锁和类的锁不冲突（也就是不同步）
 */
public class Demo {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + " m1 start....");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {e.printStackTrace();}
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    /**
     * 测试两个线程同时进入synchronized 方法
     */
    public static synchronized void m11(){
        System.out.println(Thread.currentThread().getName() + " m11 start....");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {e.printStackTrace();}
        System.out.println(Thread.currentThread().getName() + " m11 end");
    }

    public void normal(){
        System.out.println(Thread.currentThread().getName() + " normal start....");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {e.printStackTrace();}
        System.out.println(Thread.currentThread().getName() + " normal end");
    }

    public  void m2(){
        System.out.println(Thread.currentThread().getName() + " m2 start....");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 end");
    }

    public void m3(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " m3 start....");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " m3 end");
        }
    }

    public void m4(){
        synchronized (Demo.class){
            System.out.println(Thread.currentThread().getName() + " m4 start....");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " m4 end");
        }
    }

    public static void main1(String args[]){
        Demo demo = new Demo();
        new Thread(demo::m1).start();
        new Thread(demo::m2).start();
    }

    public static void main2(String args[]){
        Demo demo = new Demo();
        new Thread(demo::m3).start();
        new Thread(demo::m2).start();

    }

    public static void main(String args[]){
        Demo demo = new Demo();
        //new Thread(demo::m4).start();
        //new Thread(demo::m2).start();

        new Thread(demo::m1).start();
        new Thread(Demo::m11).start();
        new Thread(demo::normal).start();
    }



}
