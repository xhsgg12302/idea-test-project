package _algorithm.search;


import org.junit.Assert;
import org.junit.Test;

/**
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @author 12302
 * @date 2024/1/7
 * @since 1.0
 */
public class BinarySearch {

    @Test
    public void testBinarySearch(){
        int[] arr = new int[]{4,8,10, 16,18,20,50,100};

        Assert.assertEquals("", binarySearch(arr, 0, arr.length - 1, 4), 0);
        Assert.assertEquals("", binarySearch(arr, 0, arr.length - 1, 16), 3);
        Assert.assertEquals("", binarySearch(arr, 0, arr.length - 1, 100), 7);
        Assert.assertEquals("", binarySearch(arr, 0, arr.length - 1, 12302), -1);
    }

    private static int binarySearch(int[] arr, int left, int right, int key) {
        if(left > right) { return -1;}

        int mid = (left + right) / 2;
        int midKey = arr[mid];

        if(key > midKey){ return binarySearch(arr, mid + 1, right, key); }
        if(key < midKey){ return binarySearch(arr, left, mid, key); }
        return mid;
    }
}
