package _algorithm.sort;

import org.apache.commons.lang3.time.StopWatch;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.function.Consumer;

public class SortUtils {

    /**
     * 根据给定的参数生成整形元素数组
     * @param n
     * @param min
     * @param max
     * @return
     */
    public static int[] generatorByRandomObject(int n ,int min, int max){
        Random random = new Random();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = random.nextInt(max-min) + min;
        }
        return ints;
    }

    /**
     * 根据给定的参数生成整形元素数组
     * @param n
     * @param min
     * @param max
     * @return
     */
    public static int[] generatorByMathRandom(int n ,int min, int max){
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = (int)(Math.random() * (max - min)) + min;
        }
        return ints;
    }


    /**
     * 交换数组中的两个下标对应的值
     * @param raw
     * @param x
     * @param y
     */
    public static void switchNumberByTemp(int[] raw,int x, int y){
        int temp = raw[x];
        raw[x] = raw[y];
        raw[y] = temp;
    }


    /**
     *  需要注意的地方是，两个数值一样的话 ^ 后为零
     * @param raw
     * @param x
     * @param y
     */
    public static void switchNumberByXor(int[] raw, int x, int y ){
        if(raw[x] == raw[y]) return;
        raw[x] = raw[x] ^ raw[y];
        raw[y] = raw[x] ^ raw[y];
        raw[x] = raw[x] ^ raw[y];
    }


    /**
     * 性能测试方法 Instant
     * @return
     */
    public static long performanceByInstant(Consumer<int[]> consumer){
        Instant start = Instant.now();
        consumer.accept(null);
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis(); // 单位为毫秒;
    }

    /**
     * 性能测试方法 StopWatch
     * @return
     */
    public static long performanceByStopWatch(Consumer<int[]> consumer){
        StopWatch watch = new StopWatch();
        watch.start();
        consumer.accept(null);
        watch.stop();
        return watch.getTime();  //单位为毫秒
    }


}
