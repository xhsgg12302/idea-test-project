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

        if (index < 0 || capacity <= 0)
            return 0;

        // 不放第index个物品所得价值
        int res = solveKS(w, v, index - 1, capacity);
        // 放第index个物品所得价值（前提是：第index个物品可以放得下）
        // 不放的话，容量还有，可以装入其他价值大的物品，放入的话，容量变小，但是价值变大了。
        
        
        // 可以从后往前看，假如index= 3 ，容量为初始容量4，，则
        // 放入第四个物品的话，容量-2，价值+15,  + 剩余三个物品在剩余容量中的最大价值。 此例中为 选1号物品 价值 12，
        // 不放第四个物品的话，容量=4，价值 剩余三个物品在 初始容量中的最大值，此例中为 30.
        if (w[index] <= capacity) {
            res = Math.max(res,v[index] + solveKS(w, v, index - 1, capacity - w[index]));
        }
        return res;
    }

    @Test
    public void test(){
        int[] w = {2,1,3,2};
        int[] v = {12,10,20,15};
        System.out.println(solveKS(w,v,3,4));
    }
}
