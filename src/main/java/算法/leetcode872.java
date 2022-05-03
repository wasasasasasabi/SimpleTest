package 算法;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 叶子相似的树
 * https://leetcode-cn.com/problems/leaf-similar-trees/
 */
public class leetcode872 {


    public static void main(String[] args) {
        TreeNode root1 = new TreeNode();
    }

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {

        String resStr1 = dfs(root1,"");
        String resStr2 = dfs(root2,"");
        return resStr1.equals(resStr2);
    }

    public static String dfs(TreeNode root, String resStr){
        if(null == root){
            return resStr;
        }
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        if(leftNode == null && rightNode == null){
            return resStr +","+ root.val;
        }

        return dfs(leftNode,resStr) + dfs(rightNode,resStr);
    }


    @Data
    @Builder(toBuilder = true)
      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
}
