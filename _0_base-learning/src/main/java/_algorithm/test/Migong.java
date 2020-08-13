package _algorithm.test;

public class Migong {

    public static void main(String[] args) {
        int [][] map = new int[][]{
                {1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1},
                {1,0,1,0,0,0,1},
                {1,1,1,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,0,0,0,1,0,1},
                {1,1,1,1,1,1,1}
        };

        setWay(map,1,1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2){
            return true;
        }else{
            if(map[i][j] == 0){
                map[i][j] = 2 ;
                if(!setWay(map, i+1, j)){
                    return true;
                }else if(setWay(map, i, j + 1)){
                    return true;
                }else if(setWay(map, i - 1 ,j)){
                    return true;
                }else if(setWay(map, i ,j - 1)){
                    return true;
                }else{
                    map[i][j] = 3;
                    return false;
                }
            }else{
                return false;
            }
        }
    }
}
