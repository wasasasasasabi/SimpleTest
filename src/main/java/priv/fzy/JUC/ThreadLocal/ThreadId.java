package priv.fzy.JUC.ThreadLocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 官方示例
 */
public class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {//每个线程都第一个调用get都会调用这个方法初始化threadId的值
                    return nextId.getAndIncrement();//利用AtomicInteger的cas+volatile来防止并发问题
                }
            };

    public static int get() {
        return threadId.get();//同过thread对象拿到该thread的threadMap,然后再通过threadId这个threadLocal对象,从threadMap中拿到对应的值
    }

    public static void main(String[] args) {
        ThreadId t = new ThreadId();
        System.out.println(ThreadId.get());
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                System.out.println(ThreadId.get());
//            }).start();
//        }
    }
}
