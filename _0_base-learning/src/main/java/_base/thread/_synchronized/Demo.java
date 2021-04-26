package _base.thread._synchronized;

/**
 *
 * 加synchronized的方法，线程会读锁，
 * 而没有加的线程不会读锁，直接进入执行
 */
public class Demo {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + " m1 start....");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
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
        new Thread(demo::m4).start();
        new Thread(demo::m2).start();

    }
}
