package _algorithm.knapsack;

import org.junit.Test;

public class Knapsack01 {
    /**
     * 解决背包问题的递归函数
     *
     * @param w        物品的重量数组
     * @param v        物品的价值数组
     * @param index    当前待选择的物品索引
     * @param capacity 当前背包有效容量
     * @return 最大价值
     */
    private static int solveKS(int[] w, int[] v, int index, int capacity) {

        //基准条件：如果索引无效或者容量不足，直接返回当前价值0
        if (index < 0 || capacity <= 0)
            return 0;

        //不放第index个物品所得价值
        int res = solveKS(w, v, index - 1, capacity),temp = res;
        int resP = 0;
        //放第index个物品所得价值（前提是：第index个物品可以放得下）
        if (w[index] <= capacity) {
             resP = v[index] + solveKS(w, v, index - 1, capacity - w[index]);
            res = Math.max(res,resP);
        }
        for (int i = 0; i < 4; i++) {
            if(index == 3-i){
                System.out.println("index:"+ index  + "\t" + "capacity："  + capacity + "\t" + "不放：" +temp + "\t" + "放：" + resP);
            }
        }
        return res;
    }

    public static int knapSack(int[] w, int[] v, int C) {
        int size = w.length;
        return solveKS(w, v, size - 1, C);
    }

    @Test
    public void test(){
        int[] w = {2,1,3,2};
        int[] v = {12,10,20,15};
        System.out.println(knapSack(w,v,4));
        String abc = "  hello ";
        abc.trim();
    }
}
