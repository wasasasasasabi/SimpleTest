package 算法;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class leetcode_39 {

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,5};
        combinationSum(nums,8);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(0,target,candidates,path,res);
        return res;
    }

    public static void dfs(int start,int target, int[] nums, Deque<Integer> path, List<List<Integer>> res){
        if(target < 0){
            return;
        }

        if(target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            path.addLast(nums[i]);

            dfs(i,target-nums[i],nums,path,res);

            path.removeLast();
        }
    }
}
