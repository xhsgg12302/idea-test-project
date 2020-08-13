package _algorithm.number;

import org.junit.Test;

public class IsPalaNumber {

    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;


        int y = 0;
        long base = 10;
        int length = String.valueOf(x).length();
        for(int i = 1; i <= length; i ++){
            long temp = x % base / (base / 10);
            for(int j = length - i; j > 0 ; j --){
                temp *= 10;
            }
            y += temp;
            base = base * 10;
        }
        if(x == y)
            return true;
        return false;
    }


    @Test
    public void test(){
        //System.out.println(isPalindrome(121));
        //System.out.println(isPalindrome(0));
        System.out.println(isPalindrome(1000000001));
    }
}
