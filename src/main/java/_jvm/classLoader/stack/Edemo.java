package _jvm.classLoader.stack;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-17
 * @Desc:
 */
public class Edemo {


    public Edemo() {
        System.out.println("new E()");
    }

    public void getE(){
        while (true){
            System.out.println("thank you call me,that`s truth");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getEStatic(){
        while (true){
            System.out.println("thank you call me,that`s truth");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
