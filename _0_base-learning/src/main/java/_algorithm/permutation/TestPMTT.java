package _algorithm.permutation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestPMTT {

    public List<String> solvePMTT(int length) {
        if (length == 0) return Arrays.asList(new String[]{""});
        List<String> ans = new ArrayList<>();

        for (String s : solvePMTT(length - 1)) {
            ans.add("0" + s);
            ans.add("1" + s);
        }
        return ans;
    }

    public List<String> solveArrangement(String [] origin){
        if(origin.length  == 1)
            return Arrays.asList(origin);
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < origin.length; i++) {
            String current = origin[i];
            String [] temp = new String[origin.length - 1];
            System.arraycopy(origin,0,temp,0,i);
            System.arraycopy(origin,i + 1,temp,i,origin.length - 1 - i);
            for (String s : solveArrangement(temp)) {
                ans.add(current + s);
            }
            //solveArrangement()
        }
        return ans;
    }


    public List<String> solveCombination(String [] origin){
        //TODO: implement
        return null;
    }

    @Test
    public void test() {
        int i = 0 ;
        for (String s : solvePMTT(5)) {
            System.out.println(s);
            i++;
        }
        System.out.println(i);
    }

    @Test
    public void test1(){
        for (String s : solveArrangement(new String[]{"张", "王", "李"})) {
            System.out.println(s);
        }

    }
}
