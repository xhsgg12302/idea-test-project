package thread.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/6/17 15:02
 * @Description:
 */
public class ReentrantLockTest {
    ReentrantLock rt = new ReentrantLock();
    public static int origin = 0;

    public void add(){
        rt.lock();
        origin ++;
        System.out.println(Thread.currentThread().getName()+"\t"+origin);
        rt.unlock();
    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        for(int i = 0;i < 10;i++){
            new Thread(new MyThread(reentrantLockTest)).start();
        }
    }
}
class MyThread implements Runnable{
    private ReentrantLockTest reentrantLockTest;
    public MyThread(ReentrantLockTest reentrantLockTest){
        this.reentrantLockTest = reentrantLockTest;
    }

    @Override
    public void run() {
        for(int j = 0 ; j <10 ; j++){
            reentrantLockTest.add();
        }
    }
}
