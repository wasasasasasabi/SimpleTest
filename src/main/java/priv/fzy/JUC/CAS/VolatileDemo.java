package priv.fzy.JUC.CAS;

public class VolatileDemo {
    private static int x=0,y=0;
    private static int a=0,b=0;

    public static void main(String[] args) {
        int i=0;
        for(;;){
            i++;
            x=0;y=0;
            a=0;b=0;
            Thread one = new Thread(()->{

                a=1;//1
                x=b;//2
            });

            Thread two = new Thread(()->{
                b=1;//3
                y=a;//4
            });
            one.start();two.start();

            try {
                one.join();two.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String res = "第"+i+"次"+"("+x+","+y+")";
            if(x==0&&y==0){//出现了重排序 ,4 2 先于1 3执行了；或者出现内存可见性问题；
                System.out.println(res);
                break;
            }
        }
    }
}
