package thread.teststaticmethod;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-18
 * @Desc:
 */
public class SharpFile {

    public volatile static int temp;

    public static String getString(){
        return null;
    }


    public synchronized static void setString(int z){
        temp = z;

        if(temp != z){
            System.out.println(z + "\t" + temp + "\t" +Thread.currentThread().getName());
        }
    }

}
