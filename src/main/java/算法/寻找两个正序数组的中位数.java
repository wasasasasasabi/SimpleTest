package 算法;

/*
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 */
public class 寻找两个正序数组的中位数 {

    public static void main(String[] args) {
        int nums1[] = {2,7,15,17};
        int nums2[] = {3,6,8,17};
        //解法1
        System.out.println(findMedianSortedArrays01(nums1, nums2));
        //解法2
        System.out.println(findMedianSortedArrays02(nums1, nums2));
    }

    /**
     *
     * 解法1：归并排序解法，比较暴力。
     * 时间复杂度就是len1+len2既m+n
     * 空间复杂度则是O(m+n)，需要额外的空间
     */
    public static double findMedianSortedArrays01(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int lenSum = len1+len2;
        if(len1==0){
            if (len2 % 2 == 0) {
                return (nums2[len1 / 2 - 1] + nums2[len1 / 2]) / 2.0;
            } else {

                return nums2[len1 / 2];
            }
        }else if(len2 == 0){
                if (len1 % 2 == 0) {
                    return (nums1[len1/ 2 - 1] + nums1[len1 / 2]) / 2.0;
                } else {
                    return nums1[len1 / 2];
                }
        }

        int pos1=0,pos2=0,tempPos=0;
        int tempArr[] = new int[len1+len2];
        //归并nums1以及nums2这两个有序数组
        while(pos1<=len1-1&&pos2<=len2-1){
            if(nums1[pos1]<=nums2[pos2])
                tempArr[tempPos++]=nums1[pos1++];
            else
                tempArr[tempPos++]=nums2[pos2++];
        }

        //处理nums1剩余数组
        while(pos1<=len1-1){
            tempArr[tempPos++]=nums1[pos1++];
        }
        //处理nums2剩余数组
        while(pos2<=len2-1){
            tempArr[tempPos++]=nums2[pos2++];
        }
        if(tempArr.length%2==0)
            return (tempArr[lenSum/2-1]+tempArr[lenSum/2])/2.0;
        else
            return tempArr[lenSum / 2];
    }

    /**
     * 解法2 时间复杂度 (len/2)+1次，由于len=m+n 所以还是算是O(m+n)
     * 首先判断nums1和nums2的长度和是基数还是偶数
     * 奇数：由于nums1和nums2本身就是有序的，所以取到第（len+1）/2 个数就行
     * 利用两个指针nStart ,mStart分别指向nums1和nums2中的元素，根据nums1[nStart]和nums[mStart]的大小判断移动哪个指针，指到移动到第（len+1）/2 次。
     * 偶数：同理移动到第len/2次和(len/2)+1次就ok
     *
     * 需要注意的点：需要考虑到其中一个数组很短的情况，此时需要停止利用两个指针nStart或mStart的移动
     */
    public static double findMedianSortedArrays02(int[] nums1, int[] nums2) {
        int n=nums1.length,m=nums2.length;
        int len = n+m;
        int nStart=0,mStart=0;
        int left=0,right=0;
        for (int i = 0; i <=len/2 ; i++) {
            left=right;
            if(nStart<n&&(mStart>=m||nums1[nStart]<nums2[mStart])){
                right=nums1[nStart++];
            }else {
                right=nums2[mStart++];
            }
        }
        if((len&1)==0)//偶数&1等于0
            return (left+right)/2d;
        else
            return right;

    }

}
