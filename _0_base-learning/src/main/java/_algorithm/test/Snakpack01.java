package _algorithm.test;

import org.junit.Test;

public class Snakpack01 {

    public int func(int[] weight,int[] value ,int i, int j){
        if( i<0 || j <= 0) return 0;
        if(weight[i] > j){
            return func(weight,value,i-1,j);
        }else{
            return Math.max(func(weight,value,i-1,j),func(weight,value,i-1,j-weight[i]) + value[i]);
        }
    }

    @Test
    public void test(){
        int[] weight = new int[]{1, 4, 3};
        int[] value = new int[]{1500, 3000, 2000};
        System.out.println(func(weight,value,weight.length-1,4));
    }
}
