package _algorithm.test;


import org.junit.Test;

import java.util.Arrays;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if(nums.length == 0)
            return 0;
        int endIndex = nums.length - 1;
        for(int i = 0; i < nums.length; i++ ){
            if(nums[i] == val && endIndex >= i){
                for(int j = i; j < nums.length -1 ; j ++ ){
                    nums[j] = nums[j+1];
                }
                endIndex--;
                i--;
                System.out.println("==");
            }else if(endIndex < i){
                break;
            }
        }
        System.out.println(endIndex);
        System.out.println(Arrays.toString(nums));
        return endIndex;
    }

    @Test
    public void test(){
        int [] nums = {0,1,2,2,3,4,0,2};
        int i = removeElement(nums, 2);
        System.out.println(i);
    }
}
