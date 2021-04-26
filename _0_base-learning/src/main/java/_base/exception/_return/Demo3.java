package _base.exception._return;

import org.junit.Test;

/**
 * 如果finally中没有return，那么 finally中修改的值对原来准备返回的有影响吗
 */
public class Demo3 {
    public static int execution(){
        int b = 20;
        try{
            return b += 80;
        }catch (Exception e){

        }finally{
            /* 还有一个可变对象map的样例*/
            b = 101;
        }
        return b;
    }

    @Test
    public void test(){
        System.out.println(execution());
    }
}
