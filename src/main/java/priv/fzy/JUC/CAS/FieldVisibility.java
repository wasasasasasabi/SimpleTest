package priv.fzy.JUC.CAS;

/**
 * 演示可见性问题
 */
public class FieldVisibility {
    int a = 1;
    int b = 2;

    //给a 赋值, 并把值给b
    private void change() {
        a = 3;
        b = a;
    }

     //打印出a b
    private void print() {
        System.out.println("b=" + b + ";a=" + a);
    }
    //会出现b=3;a=1的情况，原因是因为change的线程把a赋值为3，然后把b赋值为a
    //但打印的线程由于可见性的原因 b获得的是新值，但a确实主存中的初始值
    public static void main(String[] args) {
        int i =0;
        while (true) {
            i++;
            FieldVisibility test = new FieldVisibility();
            new Thread(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //给 a b 重新赋值
                test.change();
            }).start();

            new Thread(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //给 a b 打印出来
                test.print();
            }).start();
        }
    }
}
