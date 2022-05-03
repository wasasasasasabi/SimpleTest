package priv.fzy.JUC.AQSLearning;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
//顺序打印abc，比直接用object的wait＋notifyAll要好，因为可以利用condition指定唤醒某个线程
public class ConditionTest {
    private int signal;//信号量 用来控制bac的输出顺序
    Lock lock = new ReentrantLock();//
    Condition a = lock.newCondition();
    Condition b = lock.newCondition();
    Condition c = lock.newCondition();//这三个内部类都指向同一个lock


    public static void main(String[] args) {
        ConditionTest ct = new ConditionTest();
        A a = new A(ct);
        B b = new B(ct);
        C c = new C(ct);
        new Thread(a,"线程A").start();
        new Thread(b,"线程B").start();
        new Thread(c,"线程C").start();
    }

    public void a() {
        lock.lock();
        while(signal!=0){
            try {
                a.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        signal++;
        b.signal();
        lock.unlock();
    }
    public void b(){
        lock.lock();
        while (signal!=1){
            try {
                b.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("b");
        signal++;
        c.signal();
        lock.unlock();
    }
    public void c(){
        lock.lock();
        while (signal!=1){
            try {
                System.out.println("c的while循环中");
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        signal=0;
        a.signal();
        lock.unlock();
    }
    static class A implements Runnable {
        private ConditionTest c;

        public A(ConditionTest c) {
            this.c = c;

        }

        @Override
        public void run() {
            while (true) {
                c.a();
            }
        }
    }

    static class B implements Runnable {
        private ConditionTest c;

        public B(ConditionTest c) {
            this.c = c;

        }

        @Override
        public void run() {
            while (true) {
                c.b();
            }
        }
    }

    static class C implements Runnable {
        private ConditionTest c;

        public C(ConditionTest c) {
            this.c = c;

        }

        @Override
        public void run() {
            while (true) {
                c.c();
            }
        }
    }
}