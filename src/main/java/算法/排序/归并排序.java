package 算法.排序;

import java.util.Arrays;

/**
 * 递归以及非递归实现
 * 递归：
 *  时间复杂度：每一趟归并为O(n),共log2n趟，所以时间为O(nlog2n)
 *  思路：递归排序的核心是merge(int[] arr, int start, int mid, int end)函数，
 *  讲[start~mid-1]和[mid~end]部分的数据合并，递归代码是使用递归得到mid，一步步分解数组。
 * 非递归：
 *  时间复杂度：O（n）
 *  思路：　非递归时，我们直接定义要合并的小数组长度从1开始，在较小的长度数组都合并完成后，令长度*2，继续进行合并，直到合并完成。也就是直接从底向上归并
 */
public class 归并排序{
    public static void main(String[] args) {
        int nums01[] ={9,5,2,7,12,4,3,1,11};
        int length = nums01.length;
        merge_sort(nums01,nums01.length);
        int nums02[] ={9,5,2,7,12,4,3,1,11};
        merge_sort02(nums02);
        System.out.println();
    }
    //归并排序入口_非递归实现
    public static void merge_sort02(int nums[]){
        int len=1;
        /*
            第一轮while:归并第1-2，3-4，5-6。。个元素 此时len=1
            第二轮while:归并1-4，5-8.。个元素 此时len=2
            第三轮while：归并1-8，9-16。。个元素，此时len=4
        */
        while(len<nums.length){//第n-1一次是nusm.length的一半以上，那么第n次久会超过nums.length，所以用(len<nums.length)进行判断就行
            for (int i = 0; i < nums.length;i=i+2*len ) {
                merge(nums,i,len);
            }
            len*=2;
        }
    }
    //递归排序_非递归实现
    public static void merge(int nums[],int left,int len){
        int posLeft = left;
        int mid = left+len;
        int posRight=mid;
        int right = posRight+len;
        int posTemp=0;
        int tempArr[] = new int[2*len];

        while(posLeft<mid&&posRight<right&&posRight<nums.length){
            if(nums[posLeft]<nums[posRight]){
                tempArr[posTemp++]=nums[posLeft++];
            }else {
                tempArr[posTemp++]=nums[posRight++];
            }
        }

        //排序左边剩余部分
        while (posLeft<mid&&posLeft<nums.length){
            tempArr[posTemp++]=nums[posLeft++];
        }
        //排序右边剩余部分
        while (posRight<right&&posRight<nums.length){
            tempArr[posTemp++]=nums[posRight++];
        }

        int count=0;
        while(left<right&&left<nums.length){
            nums[left++]=tempArr[count++];
        }
    }


    //归并排序入口_递归实现
    public static void merge_sort(int nums[],int len){
        //分配一个辅助归并的数组
        int tempArr[] = new int[len];
        merge(nums, tempArr, 0, len - 1);
        System.out.println(1);
    }

    //归并排序_递归实现
    public static void merge(int nums[],int tempArr[],int left,int right){

        //如果只有一个元素那么本身就是有序的，那么需要被归并
        if(left<right){//那至少有两个元素，进行划分
            int mid=(left+right)/2;
            //归并左边
            merge(nums,tempArr,left,mid);
            //归并右边
            merge(nums,tempArr,mid+1,right);
            //合并左边右边并进行排序
            merge(nums,tempArr,left,right,mid);
        }
    }

    //将排序后的左右分组按顺序放进临时数组_递归实现
    public static void merge(int nums[],int tempArr[],int left,int right,int mid){
        int posLeft = left;
        int posRight=mid+1;
        int posTemp = left;//临时数组所用的下标
        //归并左右两部分
        while(posLeft<=mid&&posRight<=right){
            if(nums[posLeft]<nums[posRight]){
                tempArr[posTemp++]=nums[posLeft++];
            }else{
                tempArr[posTemp++]=nums[posRight++];
            }
        }

        //归并左半边剩下的部分
        while(posLeft<=mid){
            tempArr[posTemp++]=nums[posLeft++];
        }
        //归并右半边剩下的部分
        while(posRight<=right){
            tempArr[posTemp++]=nums[posRight++];
        }

        //把合并后的数组拷回原来的数组
        while(left<=right){
            nums[left]=tempArr[left++];
        }
    }

}
