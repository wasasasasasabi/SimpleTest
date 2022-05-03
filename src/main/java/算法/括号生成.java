package 算法;

import java.util.*;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class 括号生成 {

    public static void main(String[] args) {
        List<String> strings = generateParenthesis(2);
        strings.forEach(res-> System.out.println(res));
    }

    public static List<String> generateParenthesis(int n) {
//        List<String> res = new ArrayList<>();
//        if (n <= 0) return res;
//        dfs(n, "", res, 0, 0);
        List<String> res = bfs(n);
        return res;
    }

    /**
     * 深度优先遍历
     *  把括号排列抽象成二叉树，列出所有可能，然后进行剪枝，大概就像下图那样
     *                  ""
     *                  /\
     *              "("   ")"<-剪掉
     *              /  \
     *           "(("   "()"
     *           /  \    /\
     *   剪掉->"(((" "(()"
     * @param n 括号对数
     * @param s 当前节点的括号组合字符串
     * @param res  结果集
     * @param left 左括号数量
     * @param right 右括号数量
     */
    private static void dfs(int n, String s, List<String> res, int left, int right) {

        if(left>n||left<right)//如果左括号超过一半或者右括号比左括号少，那么进行剪枝
            return;
        if(s.length()==2*n){//说明访问到叶子节点了，即已经有n对括号了，那么则回溯
            res.add(s);
            return;
        }

        dfs(n,s+"(",res,left+1,right);//往左节点走
        dfs(n,s+")",res,left,right+1);//往右节走
    }

    /**
     * 广度优先遍历---层级遍历，
     * @param n
     * @return
     */
    public static List<String> bfs(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }

    static class Node {
        /**
         * 当前得到的字符串
         */
        private String res;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        public Node(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }
}
