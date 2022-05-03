package 算法;

import java.util.Arrays;
import java.util.List;

/**
 * 双指针法
 * 时间复杂度：O(nlogn)+O(n^2)
 */
public class 最接近的三数之和 {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);//先排序

        int res=nums[0]+nums[1]+nums[2];
        for (int i = 0; i <nums.length-2; i++) {
            int start=i+1;
            int end=nums.length-1;
            while(start<end){

                int sum = nums[i]+nums[start]+nums[end];
                if(Math.abs(sum - target)<Math.abs(res-target))
                    res=sum;
                if (sum - target>0)
                    end--;
                else if(sum - target<0){
                    start++;
                }else
                    return res;//sum和target相同直接返回

            }
        }
        return res;
    }
    public static void main(String[] args) {
        int nums[]={1,2,4,8,16,32,64,128};
        threeSumClosest(nums,82);
    }
}
