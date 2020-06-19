package _algorithm.test;

import org.junit.Test;

public class IsAnagram {

    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;

        for (char c : s.toCharArray()) {
            int index = t.indexOf(c);
            if (index == -1)
                return false;
            //t = t.replace(c, ' ');
            t = t.replaceFirst(c+ "","");

        }
        return true;
    }

    @Test
    public void test(){
        System.out.println(isAnagram("abaaac","acbaaa"));
    }

}
