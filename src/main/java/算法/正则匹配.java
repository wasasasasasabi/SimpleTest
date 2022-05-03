package 算法;

/**
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 *  0 <= s.length <= 20
 *  0 <= p.length <= 30
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符.和*。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 */
public class 正则匹配 {
    public static void main(String[] args) {
        String s="aabbbbbbbccccb";
        String p = "c*a*b*";
    }

    /**
     * 递归：
     *      逻辑比较清晰简单，看代码注释既可
     *
     */
    public static boolean isMatch02(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean match =false;
        if (s.length()>0)
            match = s.charAt(0)==p.charAt(0)||p.charAt(0)=='.';//第一个字符是否匹配
        if(p.length()>1&&p.charAt(1)=='*'){//如果p的下一个字符是*
            //
            return isMatch02(s,p.substring(2))//一次也不匹配，剔除掉*以及前面的字符
                    ||(match&&isMatch02(s.substring(1),p));//匹配n次，那么去掉s的第一个就好
        }else {//p.charAt（1）不是*号的情况
            return (match&&isMatch02(s.substring(1),p.substring(1)));//如果第一个字符匹配才回去继续去掉s和p的第一个字符继续递归，否则返回false。
        }
    }
    /**
     * 动态规划：
     * 情况1：s[i-1]和p[j-1]是匹配的，那么dp[i][j]=dp[i-1][j-1]
     * 情况2：s[i-1]和p[j-1]是不匹配的
     *  ①：p[j-1]不为*号，那就是真的不匹配
     *  ②：p[j-1]为*号,当s[i-1]和p[j-2]匹配时，则考虑三种情况
     *      p[j-1]p[j−1] 星号可以让 p[j-2]p[j−2] 在 p 串中消失、出现 1 次、出现 >=2 次，
     *      消失：dp[i][j] = dp[i][j-2]
     *      出现一次，s去掉末位i，p去掉末位*以及*前的一位，既j-2:dp[i][j] = dp[i-1][j-2]
     *      出现n次：dp[i][j]=dp[i-1][j]
     *
     */
    public boolean isMatch(String s, String p) {
        int sLen =s.length(),pLen=p.length();
        boolean dp[][] = new boolean[sLen][pLen];

        //初始化 第0个和第0个数组肯定能匹配
        dp[0][0] = true;
        for (int j = 1; j < pLen + 1; j++) {//如果p的j-1个是*号的话，那么p的j-2到j-1可以匹配0次
            if (p.charAt(j-1) == '*') dp[0][j] = dp[0][j - 2];
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {//s的第i个和p的第j个能直接匹配
                    dp[i][j] = dp[i - 1][j - 1];
                }else if(p.charAt(j-1)=='*'){//第i个和第j个不匹配，且第j个为*的时候
                    //第i个能和第j-1个匹配上
                    if(s.charAt(i-1)==p.charAt(j-2)||p.charAt(j-2)=='.'){
                        dp[i][j] = dp[i-1][j-2]//*号能匹配一次的情况
                                || dp[i][j-2]//*号一次也不能匹配的情况
                                || dp[i-1][j];//*号能匹配n次的情况
                    }else {//第i个能和第j-1个匹不上，那也可以让*号直接去掉第j-1个字符
                        dp[i][j] = dp[i][j-2];
                    }

                }
            }
        }
        return dp[sLen][pLen];
    }
}
