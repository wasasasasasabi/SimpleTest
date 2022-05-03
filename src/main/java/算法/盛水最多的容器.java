package 算法;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。

 */
public class 盛水最多的容器 {
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{4, 3, 2, 1, 4}));
    }
    /**
     * 双指针解法
     *  用一个变量记录目前最大容量
     *  让i，j指针分别从两端开始移动
     *      如果nums[i]<nums[j],既第i个height比第j个height小，那么i++,计算面积并对比
     *      如果nums[j]<nums[i]，既第j个height比第i个height小，那么j--，计算面积并对比
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int len = height.length;
        int res = 0;
        int i =0,j=len-1;
        //移动短的那个板子，因为移动长的板子，根据短板效应，移动后的面积肯定比原来小，
        //如果移动短的板子，面积可能会变小，也可能会变大
        while(i<j){
            if(height[i]<height[j]){
                res=res>(height[i]*(j-i))?res:(height[i]*(j-i));
                i++;
            }else{
                res=res>(height[j]*(j-i))?res:(height[j]*(j-i));
                j--;
            }
        }
        return res;
    }
}
