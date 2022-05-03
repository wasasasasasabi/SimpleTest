package 算法;

import java.util.Arrays;

public class 在排序数组中查找元素的第一个和最后一个位置 {


    public static void main(String[] args) {
        int[] nums = ArrayUtil.getNums(10, 10);
        Arrays.stream(nums).forEach(System.out::print);

        int[] ints = searchRange(new int[]{5, 7, 7, 8, 8,10}, 8);
        System.out.println("\n");
        Arrays.stream(ints).forEach(System.out::print);
    }

    public static int[] searchRange(int[] nums, int target) {

        if(nums.length == 1 && nums[0] == target){
            return new int[]{0,0};
        }
        int firstPos = findFirstPos(nums, target);
        if (firstPos == -1)
            return new int[]{-1, -1};
        int lastPos = findLastPos(nums, target);

        return new int[]{firstPos, lastPos};
    }

    private static int findFirstPos(int nums[], int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if(nums[mid]>target){
                right = mid-1;
            }else if(nums[mid]<target){
                left = mid+1;
            }else if(nums[mid] == target){
                right = mid;
            }

            if(nums[left] == target)
                return left;
        }
        return -1;
    }

    private static int findLastPos(int nums[], int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;

        while (left<right){
            //+1是为了使left rigth 的平均数向上取整，不然
            mid = (left + right + 1)/2;
            if(nums[mid]>target){
                right = mid - 1;
            }else if(nums[mid]<target){
                left = mid + 1;
            }else if(nums[mid]==target){
                left = mid;
            }

            if(nums[right]==target)
                return right;
        }
        return -1;
    }
}
