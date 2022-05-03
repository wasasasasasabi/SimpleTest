package 算法;

public class leetcode_58 {


    public static void main(String[] args) {

    }
    public static int lengthOfLastWord(String s) {
        if (s.length() == 1) return 1;

        int index = s.length()-1;
        while (s.charAt(index) == ' '){
            index--;
        }
        int len = 0;
        while (index>=0&&s.charAt(index)!= ' '){
            len++;
            index--;
        }
        return len;
    }
}
