package _algorithm.test;

import java.util.ArrayList;
import java.util.List;

/**
 * _ Q _ _
 * _ _ _ Q
 * Q _ _ _
 * _ _ Q _
 */
public class N_Queue {

    public static void main(String[] args) {
        N_Queue n_queue = new N_Queue();
        System.out.println(n_queue.solveNQueens(8));
    }

    public List<List<String>> solveNQueens(int n) {
        //生成表格
        String[][] chessboard = getChessBoard(n);
        int queue = chessboard.length;
        for (int z = 0; z < queue; z++) {
            findPosition(chessboard, 0, z, queue);
            chessboard = getChessBoard(n);
        }
        return ans;
    }


    public static String[][] getChessBoard(int n) {
        String[][] temp = new String[n][n];
        for (int i = 0; i < n; i++) {
            String[] stemp = new String[n];
            for (int j = 0; j < n; j++) {
                stemp[j] = ".";
            }
            temp[i] = stemp;
        }
        return temp;
    }


    public static boolean isMatch(String[][] chessboard, int i, int j, int queue) {
        int g = i - j, s = i + j;
        for (int n = 0; n < queue; n++) {
            if ("Q".equals(chessboard[n][j])
                    || "Q".equals(chessboard[i][n])
                    || (-1 < s - n && s - n < queue && "Q".equals(chessboard[n][s - n]))     //向左划
                    || (-1 < n - g && n - g < queue && "Q".equals(chessboard[n][n - g]))) {    //向右划
                return false;
            }
        }
        return true;
    }

    public static List<List<String>> ans = new ArrayList<>();
    public static int count = 0;

    public static boolean findPosition(String[][] chessboard, int i, int j, int queue) {
        if (i == queue) {
            if (j == 0) {
                List<String> l0 = new ArrayList<>();
                for (int a = 0; a < queue; a++) {
                    String s1 = "";
                    for (int b = 0; b < queue; b++) {
                        //System.out.print(chessboard[a][b] + " ");
                        s1 += chessboard[a][b];
                    }
                    l0.add(s1);
                    //System.out.println();
                }
                ans.add(l0);
                //System.out.println("----------"+ (++count) +"-------------\n");
            }
            return false;
        } else {
            if (isMatch(chessboard, i, j, queue)) { //当前位置适合插入
                chessboard[i][j] = "Q";
                for (int q = 0; q < queue; q++) {
                    if (findPosition(chessboard, i + 1, q, queue)) {
                        return true;
                    }
                }
                chessboard[i][j] = ".";
                return false;
            }
        }
        return false;
    }

}
