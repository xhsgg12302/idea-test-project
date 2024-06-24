package _algorithm.test;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/6/18
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class HillValley {

    public static void main(String[] args) {
        int[] nums = new int[]{2,4,1,1,6,5};
        nums = new int[]{21,21,21,2,2,2,2,21,21,45};
        nums = new int[]{57,57,57,57,57,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,85,85,85,86,86,86};
        nums = new int[]{1,1,2};
        System.out.println(countHillValley(nums));
    }

    public static int countHillValley(int[] nums) {
        if (nums.length < 3) return 0;

        int count = 0;
        int index = 1, pre = 0, next = index  + 1;
        for (int i = index; next < nums.length;) {

            if(nums[pre] == nums[index]){
                pre = pre + 1;
                index = pre + 1;
                next = index + 1;
            }

            while(next < nums.length - 1 && nums[index] == nums[next]){
                index = index + 1;
                next = index + 1;
            }

            if(next < nums.length && nums[index] > nums[pre] && nums[index] > nums[next] || nums[index] < nums[pre] && nums[index] < nums[next]){
                count++;
            }

            // reset
            pre = index; index = next; next = next + 1;
        }
        return count;
    }
}
