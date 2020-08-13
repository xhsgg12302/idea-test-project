package _algorithm.array;

import org.junit.Test;

/**
 * 面试题 01.07. 旋转矩阵
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 */
public class ImageRotate {

    /**
     * 方式一：使用对一条对角线+每行对称互换 进行翻转矩阵
     * @param origin
     */
    public void Rotate(int [][] origin){
        // 对角线互换
        for (int i = 0; i < origin.length; i++) {
            for (int j = i; j < origin.length; j++) {
                if(i != j)
                    switchNumberByTemp(origin,j,i,i,j);
            }
        }
        // 每行对折互换
        for (int i = 0; i < origin.length; i++) {
            for (int j = 0; j < origin.length / 2; j++) {
                int pos = origin.length -j - 1;
                if(j != pos){
                    switchNumberByTemp(origin,i,j,i,pos);
                }
            }
        }
    }

    /**
     * 方式二：按照块进行翻转，主要就是把块里面的每一个元素，和与其一组的四个元素进行互换
     * 元素位置关系：(i,j) (j,n-i-1) (n-i-1,n-j-1) (n-j-1,i)
     * @param origin
     */
    public void rotate(int[][] origin){
        int n = origin.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = origin[i][j];
                switchNumberByTemp(origin,i,j,n-j-1, i);
                switchNumberByTemp(origin,n-j-1,i,n-i-1,n-j-1 );
                switchNumberByTemp(origin,n-i-1,n-j-1 ,j,n - i - 1 );
                origin[j][n-i-1] = temp;

            }
        }
    }

    /**
     * 打印矩阵
     * @param result
     */
    public void print(int [][] result){
        for (int[] ints : result) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 交换矩阵中的元素
     * @param raw
     * @param x
     * @param a
     * @param y
     * @param b
     */
    public static void switchNumberByTemp(int[][]raw,int x, int a ,int y, int b){
        int temp = raw[x][a];
        raw[x][a] = raw[y][b];
        raw[y][b] = temp;
    }

    @Test
    public void test(){
        int[][] origin = {
                {5, 1, 9 ,11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        Rotate(origin);
        print(origin);

        System.out.println("\n");
         int[][] origin1 = {
                {5, 1, 9 ,11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        rotate(origin1);
        print(origin1);

    }
}
