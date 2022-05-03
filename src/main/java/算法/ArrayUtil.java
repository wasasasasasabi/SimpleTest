package 算法;

import java.util.Random;

public class ArrayUtil {


    public static int[] getNums(int size,int range){
        int[] nums = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            nums[i] = r.nextInt(range);
        }
        return nums;
    }

    public static int[] getStaticNums(){
        return new int[]{5,7,7,8,8,8,8,8,8,10};
    }
}
