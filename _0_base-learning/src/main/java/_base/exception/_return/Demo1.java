package _base.exception._return;

import org.junit.Test;

/**
 * finally 语句在return语句执行之后，返回之前 执行
 */
public class Demo1 {
    public static int execution(){
        int b = 20;
        try {
            return b += 80;
        }catch (Exception e){
            System.out.println("catch block");
        }finally {
            System.out.println("finally block");
            if(b > 25){
                System.out.println("b>25,b=" + b);
            }
        }
        return b += 1;
    }


    @Test
    public void test(){
        System.out.println(execution());
    }
}
