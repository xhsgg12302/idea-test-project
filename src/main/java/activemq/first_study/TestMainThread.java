package activemq.first_study;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/1/7 13:37
 * @Description:
 */
public class TestMainThread {
    public static void main(String[] args) throws Exception{
        Thread.sleep(10000);
        while(true){
            System.out.println(Thread.currentThread().getState());
        }
    }
}
