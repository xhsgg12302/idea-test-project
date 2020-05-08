package _draft.draft.gongdi;

public class T1 {

    public static void main(String[] args) {
        calc();
    }


    public static void calc(){
        int a[] = new int[]{0,1,2,3,4,5,6,7,8,9};
        int b[] = new int[]{0,1,2,3,4,5,6,7,8,9};
        int c[] = new int[]{0,1,2,3,4,5,6,7,8,9};

        for (int i = 0 ;i < a.length ; i++ ) {
            for(int j = 0 ; j < b.length ;j ++){
                for (int n = 0 ; n < c.length ;n ++){
                    if (a[i] + b[j] + c[n] == 10) {
                        System.out.println("位置:" + i + "\t" + j + "\t" + n);
                    }
                }
            }
        }
    }
}
