package _algorithm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2021 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2021-10-23
 */
public class SearchGraph {

    /* _12302_2021/10/23_<

     A-B, B-D, D-E, D-G, E-F, F-G
     A-C, C-F,


    > */

    private static String[] vertex = { "A","B","C","D","E","F","G"};
    private static int[][] matrixbak = {
                  //A  B  C  D  E  F  G
            /*A*/ { 0, 0, 0, 1, 0, 0, 0 },
            /*B*/ { 1, 0, 1, 0, 0, 0, 0 },
            /*C*/ { 0, 0, 0, 0, 0, 1, 0 },
            /*D*/ { 0, 0, 0, 0, 1, 0, 1 },
            /*E*/ { 0, 0, 0, 0, 0, 1, 0 },
            /*F*/ { 0, 0, 0, 0, 0, 0, 1 },
            /*G*/ { 0, 0, 0, 0, 0, 0, 0 }
    };

    private static int[][] matrix = {
                  //A  B  C  D  E  F  G
            /*A*/ { 0, 1, 1, 0, 0, 0, 0 },
            /*B*/ { 0, 0, 0, 1, 0, 0, 0 },
            /*C*/ { 0, 0, 0, 0, 0, 1, 0 },
            /*D*/ { 0, 0, 0, 0, 1, 0, 1 },
            /*E*/ { 0, 0, 0, 0, 0, 1, 0 },
            /*F*/ { 0, 0, 0, 0, 0, 0, 1 },
            /*G*/ { 0, 0, 0, 0, 0, 0, 0 }
    };

    public static void main(String[] args) {
        List ans = new ArrayList<List<String>>();
        getHeads(matrix).forEach( it -> {
            List<String> temp = new ArrayList<>();
            ans.add(temp);
            searchLinked(it,matrix,temp, ans);
        });
        System.err.println(ans);

    }

    public static void searchLinked(int head,int[][] matrix, List<String> each,List<List<String>> ans){
        each.add(vertex[head]);
        if(isEnd(head)){ return; }
        boolean isUsed = false;
        List<String> bak = each.stream().collect(Collectors.toList());
        for (int i = 0 ; i < matrix.length; i++) {
            if(matrix[head][i] == 1){
                if(isUsed){ each =  bak; ans.add(each);}
                searchLinked(i, matrix, each, ans);
                isUsed = true;
            }
        }
    }

    public static List<Integer> getHeads( int[][] matrix ) {
        List<Integer> heads = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            boolean flag = false;
            for (int j = 0; j < matrix.length ; j++) {
                if( matrix[j][i] > 0 ){ flag = true; }
            }
            if(!flag){heads.add(i);}
        }
        return heads;
    }

    public static boolean isEnd( int row ){
        long count = Arrays.stream(matrix[row]).filter(it -> it == 1).count();
        return count == 0;
    }
}
