package 算法;

/**
 *实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 *
 */
public class 下一个排列 {

    public static void main(String[] args) {
        int nums[] = {3,4,3,2,1};
        nextPermutation(nums);
        System.out.println(1);
    }
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        //倒数第二个元素
        int i = nums.length-2;
        //从后往前找到第一个降序的元素.eg:对于4 5 2 6 3 1来说 第一个降序元素就是2
        while (i>=0&&nums[i+1]<=nums[i])
            i--;
        if(i>=0){//从后往前找到比nums[i]大的元素
            int j=nums.length-1;
            while (j >= 1 && ((nums[j] <= nums[i])||nums[j-1]==nums[j])){
                j--;
            }
            swap(nums, i, j);
            reverse(nums,i+1,nums.length-1);
        }else {
            //如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）
            reverse(nums,0,nums.length-1);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private static void reverse(int[] nums, int a, int b) {
        int l = a, r = b;
        while (l < r) {
            swap(nums, l++, r--);
        }
    }
}
