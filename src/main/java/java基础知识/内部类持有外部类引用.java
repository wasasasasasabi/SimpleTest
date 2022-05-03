package java基础知识;

/**
 * 在含有内部类的类编译时，系统会为内部类的构造函数（如果有多个构造函数的话，多个的都会）额外在增加一个参数，这个参数即是外部类引用，
 * 从而使得“内部类持有了外部类的引用” 这个是在编译时自动完成的
 */
public class 内部类持有外部类引用 {

    private static class MyInnerClass {
        private String innerString;
        public MyInnerClass( ) {
        }
        public MyInnerClass(String innerString ) {
            this.innerString = innerString;
        }
    }

    public static void main(String[] args) {

    }
}
