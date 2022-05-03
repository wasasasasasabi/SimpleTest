package 算法.排序;

/**
 * 选定一个pivot中心轴
 * 将大于pivot的数字放到又指针，比pivot大的放在左指针，左右指针重合则结束一次排序，该次排序将小于pivot和大于pivot的放到了左边与右边
 * 左子序列和右子序列重复以上操作
 *
 */
public class 快速排序 {
    public static void main(String[] args) {
        int nums[] = {19,97,9,18,1,8};
        quickShort(nums,0,nums.length-1);
        System.out.println();
    }

    public static void quickShort(int nums[],int L,int R){
        if(L>=R)
            return;
        int left=L,right=R;
        int pivot = nums[left];

        while(left<right){
            while(left<right&&nums[right]>=pivot){//移动right直到发现比pivot小的数字
                right--;
            }
            if(left<right){
                nums[left]=nums[right];
            }

            while(left<right&&nums[left]<=pivot){
                left++;
            }
            if(left<right){
                nums[right]=nums[left];
            }
            if(left==right){
                nums[left]=pivot;
            }

        }
        //不包含right是因为pivot在那个位置刚刚好，左边全小于pivot，右边全大于pivot，不需要移动了
        quickShort(nums,L,right-1);
        quickShort(nums,right+1,R);
    }
}
