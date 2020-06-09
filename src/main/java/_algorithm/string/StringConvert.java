package _algorithm.string;

import org.junit.Test;

/**
 * 6. Z 字形变换
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 *
 */
public class StringConvert {

    @Test
    public void test(){
        //System.out.println(convert("LEETCODEISHIRING",3));
        //System.out.println(convert("LEETCODEISHIRING",4));
        System.out.println(convert("AB",1));
    }

    public String convert(String s, int numRows) {

        int length = s.length();
        int index = 0;
        //length = length %2 == 0 ? length/2 : length/2+1;
        char [][] design = new char[numRows][length];
        boolean flag = true; int row = 0; int line = 0;
        for (int i = 0; i < length ; i++) {
           if(flag){
               for (int j = 0; j < numRows && index < s.length();j++){
                   design[j][i] = s.charAt(index ++ );
                   row = j;
               }
               flag = false;
               line = i;
           }else{
               for (int j = numRows -1 ; j >=0 && index < s.length(); j--){
                    if(j == row -1 && i == line + 1){
                        if(row -1  == 0){
                            flag = true;
                            i--;
                            continue;
                        }
                        design[j][i] = s.charAt(index ++);
                        row = row -1 ;
                        line = line + 1;
                    }else if(row == 0){
                        design[row][i] = s.charAt(index ++);
                        flag = true;
                    }
               }
           }
        }

        StringBuffer sbf = new StringBuffer();
        for (char[] chars : design) {
            for (char aChar : chars) {
                if (aChar != '\u0000') {
                    sbf.append(aChar);
                }
            }
        }
        return sbf.toString();
    }
}
