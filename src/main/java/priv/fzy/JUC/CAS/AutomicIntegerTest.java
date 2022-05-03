package priv.fzy.JUC.CAS;

public class AutomicIntegerTest {
    public static int b;

    public void set1() {
        b = 0;
    }

    public void set2() {
        b = -1;
    }

    public void check() {
        System.out.println(b);

        if (0 != b && -1 != b) {
            System.err.println("Error");
        }
    }
    public static void main(String[] args) {
        AutomicIntegerTest at = new AutomicIntegerTest();
        Thread th1 = new Thread(()->{
            while (true){
                at.set1();
            }
        },"th1");
        Thread th2 = new Thread(()->{
            while(true){
                at.set2();
            }
        },"th2");
        Thread th3 = new Thread(()->{
            while (true){
                at.check();
            }
        },"th3");
        th1.start();
        th2.start();
        th3.start();
    }
}
