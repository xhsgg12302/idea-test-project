package thread.cp;


import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/6/15 19:14
 * @Description:
 */
public class TestCp {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        for(int i = 0 ; i< 4 ;i++){
            new Thread(new Cons(restaurant)).start();
            new Thread(new Prod(restaurant)).start();
        }
    }
}

class Restaurant{
    private static List<Integer> food = new ArrayList<>(1);

    public synchronized void make(){
        //System.out.println(Thread.currentThread().getName() + "\t" + "begin");
        while(food.size()==1){
            try {
                wait();
                //System.err.println(Thread.currentThread().getName() + "\t" + "wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        food.add(1);
        System.out.println(Thread.currentThread().getName() + "\t" + "add");
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        notify();
    }

    public synchronized void eat(){
        while(food.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        food.remove(0);
        System.out.println(Thread.currentThread().getName() + "\t" + "remove");
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        notify();
    }
}

class Prod implements Runnable{

    private Restaurant restaurant;

    public Prod(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        restaurant.make();
    }
}
class Cons implements Runnable {

    private Restaurant restaurant;

    public Cons(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        restaurant.eat();
    }
}