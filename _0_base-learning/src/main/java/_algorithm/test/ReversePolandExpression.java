package _algorithm.test;

import org.junit.Assert;

import java.util.Stack;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/6/24
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class ReversePolandExpression {

    public static void main(String[] args) {

        String expression = "( ( a + b ) * ( a - c ) - d ) * (x + y)";
        //System.out.println(gen(expression));


        // 测试用例
        Assert.assertEquals("a m n - c * d - f * +",gen("a + ((m -n ) * c - d ) * f").trim());
        Assert.assertEquals("a b + a c - * d - x y + *", gen("( ( a + b ) * ( a - c ) - d ) * (x + y)").trim());
        Assert.assertEquals("a b + a c - d * - x y + *", gen("((a+b)-(a-c)*d)*(x+y)").trim());
        Assert.assertEquals("a b c * +", gen("a + b * c").trim());
        Assert.assertEquals("a b + c *", gen("(a + b) * c").trim());
        Assert.assertEquals("a b * c d / +", gen("a * b + c / d").trim());
        Assert.assertEquals("a b c * + d e / -", gen("a + b * c - d / e").trim());
        Assert.assertEquals("a b c + * d / e -", gen("a * (b + c) / d - e").trim());
        Assert.assertEquals("a b + c d - * e /", gen("(a + b) * (c - d) / e").trim());
        Assert.assertEquals("a b * c * d +", gen("a * b * c + d").trim());
        Assert.assertEquals("a b / c d * + e -", gen("a / b + c * d - e").trim());

    }

    private static String gen(String expression) {
        Stack<Character> stackTmp = new Stack<>();

        StringBuilder rst = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if(c == ' ') continue;
            if(c >= 'a' && c <= 'z'){
                rst.append(c).append(" ");
                continue;
            }
            switch (c){
                case '(':
                    stackTmp.push(c);break;
                case ')':
                    while(stackTmp.peek() != '('){
                        rst.append(stackTmp.pop()).append(" ");
                    }
                    stackTmp.pop();
                    break;
                case '+':
                case '-':
                case '*':
                case '/':

                    char pop = stackTmp.empty() ? '(' : stackTmp.peek();
                    if(priority(c, pop)){
                        stackTmp.push(c);
                    }else{
                        do {
                            rst.append(stackTmp.pop()).append(" ");
                        }while(!stackTmp.isEmpty() && !priority(c, stackTmp.peek()));
                        stackTmp.push(c);
                    }
            }
        }
        while(!stackTmp.isEmpty()){ rst.append(stackTmp.pop()).append(" ");}
        return rst.toString();
    }

    private static boolean priority(char a, char b){
        if(b == '(') return true;
        return (a == '*' || a == '/') && (b == '+' || b == '-');
    }
}
