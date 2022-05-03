package 算法;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class 最长有效括号 {
    public static void main(String[] args) {
        longestValidParentheses("(()");
    }

    /**
     * 使用栈来解决
     * （括号入栈，遇到）括号则栈顶出栈，然后计算出栈之后的栈顶的下标到当前字符下标
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int maxLen=0;
        stack.push(-1);//用来防止EmptyStackException
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='('){
                stack.push(i);
            }else{
                stack.pop();
                if (stack.isEmpty()){
                    //闭括号入栈，为什么闭括号要入栈呢，因为进入到这里说明栈里面已经没有开括号了，如果下一次还是闭括号，那么执行到stack.pop的时候会抛栈空异常，所以栈底会留有一个元素来兜底。
                    stack.push(i);
                }else {
                    maxLen=Math.max(maxLen,i-stack.peek());//计算目前栈顶元素下表到当前字符的下标的差值
                }
            }
        }
        return maxLen;
    }

    /**
     * 动态规划
     */
    public static int longestValidParentheses02(String s) {
        char[] chars = s.toCharArray();
        int dp[] = new int[chars.length];
        dp[0]=0;

        return 0;

    }
}
