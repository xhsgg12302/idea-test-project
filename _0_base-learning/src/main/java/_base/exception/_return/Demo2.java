package _base.exception._return;

import org.junit.Test;

/**
 * finally 快中的return语句会覆盖try 或者 catch 中的return
 */
public class Demo2 {

    public static int execution(){
        int b = 20;

        try{
            System.out.println("try block");
            return b += 80;
        }catch (Exception e){
            return 101;
        }finally {
            return 102;
        }
        //return 0;

    }

    @Test
    public void test(){
        System.out.println(execution());
    }

}
