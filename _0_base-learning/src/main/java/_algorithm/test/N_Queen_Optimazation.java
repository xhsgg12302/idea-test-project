package _algorithm.test;

import java.util.ArrayList;
import java.util.List;

public class N_Queen_Optimazation {
    public static void main(String[] args) {
        N_Queen_Optimazation n_queue = new N_Queen_Optimazation();
        System.out.println(n_queue.solveNQueens(8));
    }

    public static List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        //生成表格
        String[][] chessboard = getChessBoard(n);
        int queue = chessboard.length;
        findPosition(chessboard,0,queue);
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

    public static boolean findPosition(String[][] chessboard, int n, int queue) {
        if (n == queue) {
            ans.add(compact(queue,chessboard));
            return false;
        } else {
            for(int j = 0; j < queue; j++){
                if(isMatch(chessboard, n, j, queue)){
                    chessboard[n][j] = "Q";
                    if(!findPosition(chessboard, n + 1, queue)){
                        chessboard[n][j] = ".";
                    }
                }
            }
        }
        return false;
    }

    public static List<String> compact(int queue,String[][] chessboard){
        List<String> cp = new ArrayList<>();
        for (int a = 0; a < queue; a++) {
            String temp = "";
            for (int b = 0; b < queue ; b++) {
                temp += chessboard[a][b];
            }
            cp.add(temp);
        }
        return cp;
    }
}
