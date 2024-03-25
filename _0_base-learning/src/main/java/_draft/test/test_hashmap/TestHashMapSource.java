package _draft.test.test_hashmap;

import org.junit.Test;

import java.util.HashMap;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/2/26
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class TestHashMapSource {


    @Test
    public void test01(){

        System.out.println(roundUpToPowerOf2(5));
        System.out.println(roundUpToPowerOf2(7));
        System.out.println(roundUpToPowerOf2(9));
        System.out.println(roundUpToPowerOf2(2));
        System.out.println(roundUpToPowerOf2(1));
        System.out.println(roundUpToPowerOf2(8));
        System.out.println(roundUpToPowerOf2(234));

        System.out.println(tableSizeFor(5));
        System.out.println(tableSizeFor(7));
        System.out.println(tableSizeFor(9));
        System.out.println(tableSizeFor(2));
        System.out.println(tableSizeFor(1));
        System.out.println(tableSizeFor(8));
        System.out.println(tableSizeFor(16));
        System.out.println(tableSizeFor(0));


        System.out.println(tableSizeFor(1 << 30 + 1));
    }

    @Test
    public void test02() {

        System.out.println(tableSizeFor(1 << 30 + 1));

        System.out.println("柳柴".hashCode());
        System.out.println("柴柕".hashCode());

        HashMap hashMap = new HashMap(3);

        hashMap.put("hello0", "world");
        hashMap.put("hello1", "world");
        hashMap.put("hello2", "world");
        hashMap.put("hello3", "world");
        hashMap.put("hello4", "world");
        hashMap.put("hello5", "world");
        hashMap.put("hello6", "world");
        hashMap.put("hello7", "world");
        hashMap.put("hello8", "world");
        hashMap.put("hello9", "world");
        hashMap.put("hello10", "world");
        hashMap.put("hello11", "world");
        hashMap.put("hello12", "world");
        hashMap.put("hello13", "world");
        hashMap.put("hello14", "world");
        hashMap.put("hello15", "world");
        hashMap.put("hello16", "world");
        hashMap.put("hello17", "world");
        hashMap.put("hello18", "world");
        hashMap.put("hello19", "world");
        hashMap.put("hello20", "world");
        hashMap.put("hello21", "world");
        hashMap.put("hello22", "world");
        hashMap.put("hello23", "world");
        hashMap.put("hello24", "world");
        hashMap.put("hello25", "world");
        hashMap.put("hello26", "world");
        hashMap.put("hello27", "world");
        hashMap.put("hello28", "world");
        hashMap.put("hello29", "world");
        hashMap.put("hello30", "world");
        hashMap.put("hello31", "world");
        hashMap.put("hello32", "world");
        hashMap.put("hello33", "world");

    }

    @Test
    public void test03(){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(null,"abc");
        objectObjectHashMap.put(null,"def");
        System.out.println();
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * jdk 1.7 hashmap 扩容算法
     * @param number
     * @return
     */
    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }

    /**
     * jdk 1.8 hashmap 扩容算法
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
