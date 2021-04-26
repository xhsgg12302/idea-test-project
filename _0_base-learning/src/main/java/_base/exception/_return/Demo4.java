package _base.exception._return;

import org.junit.Test;

/**
 * try 中如果发生异常，则返回看情况，在catch 和 finally中都没有返回值的时候，
 * 使用结尾的return b;
 */
public class Demo4 {
    public static int execution(){
        int b = 20;
        try{
            return b /= 0;
        }catch (Exception e){
            return b += 1;
        }finally{
            b += 2;
            //return 22;
        }
        //return 23;
    }

    @Test
    public void test(){
        System.out.println(execution());
    }
}
