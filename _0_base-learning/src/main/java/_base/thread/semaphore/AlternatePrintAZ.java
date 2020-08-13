package _base.thread.semaphore;

import java.util.concurrent.Semaphore;

public class AlternatePrintAZ {

    public static void main(String[] args) {
        AZ az = new AZ();

        new Thread(()->{
            try {
                az.print1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                az.print2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                az.print3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}

class AZ{

    volatile char curr = 'A';

    Semaphore s1 = new Semaphore(1);
    Semaphore s2 = new Semaphore(0);
    Semaphore s3 = new Semaphore(0);

    void print1() throws InterruptedException{
        for(int i = 1;i <= 26 ;i +=3 ){
            s1.acquire();

                System.out.println(curr + "\t" + Thread.currentThread().getName());
                curr = (char)(curr + 1);
                s2.release();

        }
    }

    void print2() throws InterruptedException{
        for(int i = 2;i <= 26 ;i += 3 ){
            s2.acquire();

                System.out.println(curr + "\t" + Thread.currentThread().getName());
                curr = (char)(curr + 1);
                s3.release();

        }

    }

    void print3() throws InterruptedException{
        for(int i = 3;i <= 26 ;i += 3 ){
            s3.acquire();

                System.out.println(curr + "\t" + Thread.currentThread().getName());
                curr = (char)(curr + 1);
                s1.release();

        }

    }
}