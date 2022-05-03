package java基础知识;

public class InnerClassTest {


    public static void main(String[] args) {
        InnerClassTest test = new InnerClassTest();
        test.ship("a");
    }

    class IneerA{
        private int i = 11;
        public int value() {
            return i;
        }
    }

    class IneerB{
        private String label;
        public IneerB(String whereTo) {
            // TODO Auto-generated constructor stub
            label = whereTo;
        }
        String readLabel(){
            return label;
        }
    }

    public void ship(String dest) {
        IneerA a = new IneerA();
        IneerB b= new IneerB(dest);
        System.out.println(b.readLabel());
    }

}
