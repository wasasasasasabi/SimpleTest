package 算法;

/**
 * [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 *
 * 可以直接遍历，但时间复杂度为O(n)，所以进阶使用二分法来降低时间复杂度到O(logn)
 */
public class 搜索旋转排序数组 {

    public static void main(String[] args) {

    }

    public static int search(int[] nums, int target) {
        int l=0,r=nums.length-1,mid;
        while (l<=r){
            mid = l + (r - l) / 2;
            if (nums[mid]==target) return mid;
            if(nums[l]<=nums[mid]){//左半边是完全有序的一段
                if(target>=nums[l]&&target<=nums[mid]){//target在左半边
                    r=mid-1;
                }else {//如果不在左半边就肯定在右半边
                    l=mid+1;
                }
            }else{//右半边是完全有序的一段
                if(target>=nums[mid+1]&&target<=nums[r]){
                    l=mid+1;
                }else {
                    r=mid-1;
                }
            }

        }
        return -1;
    }
}
