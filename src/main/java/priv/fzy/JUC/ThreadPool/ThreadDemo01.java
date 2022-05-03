package priv.fzy.JUC.ThreadPool;

import java.util.concurrent.*;

/**
 * 多线程创建的几种方式
 * 1.继承Thread重写run
 * 2.实现Runnable接口
 * 3.Callable+FutureTask
 */
public class ThreadDemo01{
    public static class Tasker implements Runnable{
        @Override
        public void run() {
            System.out.println("ha");
        }

    }
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        ExecutorService service = Executors.newFixedThreadPool(3);
//        service.submit(new Tasker());
        FutureTask<String> ft = new FutureTask<String>(()->{
            Object o = new Object();
            synchronized (o){
                o.wait();
            }
            return "123";
        });
        Thread t1 = new Thread(ft);
        t1.start();
        new Thread(()->{
            try {
                ft.get(1,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        },"01").start();
        Thread.sleep(100);
        String s = ft.get(1,TimeUnit.SECONDS);
        System.out.println(1);
    }

}
