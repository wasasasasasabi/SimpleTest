package priv.fzy.JUC;

public class Exercise {

    private int signal = 0;//通过信号量控制abc的输出顺序
    public static Object o = new Object();
    public synchronized void a() {//加锁使用wait通信，同时保证了信号量的内存可见性
        while (signal != 0) {
            try {
                wait();//释放cpu的执行资格和执行权，同时释放锁
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("a");
        signal++;
        notifyAll();
    }

    public synchronized void b() {
        while (signal != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("b");
        signal++;
        notifyAll();
    }

    public synchronized void c() {
        while (signal != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("c");
        signal = 0;
        notifyAll();
    }

    public static void main(String[] args) {
        Exercise e = new Exercise();
        A a = new A(e);
        B b = new B(e);
        C c = new C(e);
        a.start();
        b.start();
        c.start();

    }

}

class A extends Thread {
    private Exercise e;

    public A(Exercise e) {
        this.e = e;

    }

    @Override
    public void run() {
        while (true) {
            e.a();
        }
    }
}

class B extends Thread {
    private Exercise e;

    public B(Exercise e) {
        this.e = e;

    }

    @Override
    public void run() {
        while (true) {
            e.b();
        }
    }
}

class C extends Thread {
    private Exercise e;

    public C(Exercise e) {
        this.e = e;

    }

    @Override
    public void run() {
        while (true) {
            e.c();
        }
    }
}