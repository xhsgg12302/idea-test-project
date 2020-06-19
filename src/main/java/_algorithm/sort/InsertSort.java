package _algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class InsertSort {

    public int[] isort(int[] raw){
        int[] ans = new int[raw.length];
        for (int i = 0; i < raw.length ; i++) {
            if(i == 0){
                ans[i] = raw[i];
            }else{
                int temp = raw[i];
                for (int j = 0; j <= i; j++) {
                    if(temp <= ans[j]){
                        //所有后移
                        shift(ans,temp,j);
                        break;

                    }else{
                        if(j == i){
                            shift(ans,temp,j);
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }


    public void isortByOne(int [] raw){
        for (int i = 1; i < raw.length; i++) {
            int indexVal = raw[i];
            int index = i - 1;
            while(index >=0 && indexVal < raw[index]){
                raw[index + 1 ] = raw[index];
                index --;
            }
            raw[index + 1] = indexVal;
        }
    }


    public void shift(int[] ans,int value, int position){
        for (int i = ans.length - 2; i >= position; i--) {
            ans[i + 1] = ans[i];
        }
        ans[position] = value;
    }

    @Test
    public void test(){

        int[] ints = SortUtils.generatorByMathRandom(10, 1, 100);
        System.out.println(Arrays.toString(ints));
        ints = isort(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void test2(){
        int[] ints = SortUtils.generatorByMathRandom(10, 1, 100);
        System.out.println(Arrays.toString(ints));
        isortByOne(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void performance(){
        int[] generator = SortUtils.generatorByMathRandom(80000, 0, 800000000);
        System.out.println(SortUtils.performanceByInstant((ints)-> isortByOne(generator)) + "ms");
    }
}
