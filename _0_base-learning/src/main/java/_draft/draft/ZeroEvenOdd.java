package _draft.draft;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-09
 * @Desc:
 */

public class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    Semaphore zero = new Semaphore(1);
    Semaphore even = new Semaphore(0);
    Semaphore odd = new Semaphore(0);

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0 ; i < n ; i++){
            zero.acquire();
            printNumber.accept(0);
            if( i % 2 == 0){
                odd.release();
            }else{
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2 ; i <= n ; i += 2 ){
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1 ; i <= n ; i += 2 ){
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(4);
        new Thread(new Zero(zeroEvenOdd)).start();
        new Thread(new Even(zeroEvenOdd)).start();
        new Thread(new Odd(zeroEvenOdd)).start();
    }
}

class Zero implements Runnable{
    private ZeroEvenOdd zeroEvenOdd;
    public Zero(ZeroEvenOdd zeroEvenOdd){
        this.zeroEvenOdd=zeroEvenOdd;
    }
    @Override
    public void run() {
        try {
            zeroEvenOdd.zero((x)->System.out.println(x));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Even implements Runnable{
    private ZeroEvenOdd zeroEvenOdd;
    public Even(ZeroEvenOdd zeroEvenOdd){
        this.zeroEvenOdd=zeroEvenOdd;
    }
    @Override
    public void run() {
        try {
            zeroEvenOdd.even((x)->System.out.println(x));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Odd implements Runnable{
    private ZeroEvenOdd zeroEvenOdd;
    public Odd(ZeroEvenOdd zeroEvenOdd){
        this.zeroEvenOdd=zeroEvenOdd;
    }
    @Override
    public void run() {

        try {
            zeroEvenOdd.odd((x)->System.out.println(x));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
