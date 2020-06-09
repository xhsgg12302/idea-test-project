package _algorithm.test;

public class StringRecursion {
    public static void main(String []args) {

        String temp = "I love Hello World!";
        System.out.println(reverse(temp).trim());

    }
    public static String reverse(String temp){
        int index = temp.lastIndexOf(" ");
        //System.out.println(temp.substring(index));
        if(index != -1){
            return temp.substring(index) + reverse(temp.substring(0, index));
        }
        return " " + temp;
    }
}
