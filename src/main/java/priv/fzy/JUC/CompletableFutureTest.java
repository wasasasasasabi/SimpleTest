package priv.fzy.JUC;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * CompletableFuture用法全解
 */
public class CompletableFutureTest {
    @Test
    public void test(){
        CompletableFuture cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread());
            return "123";
        }).thenApply((res)->{
            System.out.println(Thread.currentThread());
            return "456";
        });
    }

    /**
     * 创建FutureTask异步任务
     *  验证了：
     *  FutureTask的get方法会阻塞
     *  FutureTask执行task抛出的异常会抛出到消费者线程中并被消费者再次抛出
     */
    @Test
    public void test01() throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Future<String> ft = executorService.submit(() -> {
            System.out.println("异步任务开始" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                System.out.println("异步任务抛出异常");
                throw new RuntimeException("异常对象");
            } else {
                System.out.println("异步任务结束" + Thread.currentThread().getName());
            }
            return "res";
        });
        Thread.sleep(3000);
        System.out.println("主线程start");
        System.out.println("阻塞获取结果："+ft.get());//如果又异常，异常会在这里被抛出
        System.out.println("主线程结束");
    }

    /**
     * supplyAsync / runAsync的测试
     * supplyAsync 带有返回值的异步任务
     * runAsync 不带返回值的异步任务
     * 这两个方法拥有一个重载版本，可以指定执行异步任务的Executor实现，如果不指定，默认使用ForkJoinPool.commonPool()。
     */
    @SneakyThrows
    @Test
    public void test02(){
        // 创建异步执行任务，有返回值
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" 有返回值的线程 start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(true){
                throw new RuntimeException("有返回值的线程test");
            }else{
                System.out.println(Thread.currentThread()+"有返回值的线程 exit,time->"+System.currentTimeMillis());
                return 1.2;
            }
        });
        System.out.println("有返回值的线程 main thread start,time->"+System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("有返回值的线程 run result->"+cf.get());
        System.out.println("有返回值test end");

        // 创建异步执行任务，无返回值
        CompletableFuture cf2 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread()+" 无返回值的线程 start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("无返回值的线程 test");
            }else{
                System.out.println(Thread.currentThread()+"无返回值的线程 exit,time->"+System.currentTimeMillis());
            }
        });
        System.out.println("无返回值的线程 main thread start,time->"+System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("无返回值的线程 run result->"+cf2.get());
        System.out.println("无返回值test end");

    }

    /**
     * 异步回调
     * thenApply 由执行第一个task的线程来执行本次任务
     * thenApplyAsync 将task交到线程池中执行
     */
    @Test
    public void test03(){
        ForkJoinPool pool=new ForkJoinPool();
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(()->{
            //start job1 === Thread[ForkJoinPool-1-worker-1,5,main]
            System.out.println("start job1 === "+Thread.currentThread());
            return "result of job1";
        },pool);
//        cf.thenApply((res)->{
//            //start job2 === Thread[main,5,main]
//            System.out.println("start job2 === "+Thread.currentThread());
//            System.out.println("print return of job1 === "+res);
//            return "第二个任务返回";
//        });
        cf.thenApplyAsync((res)->{
            System.out.println("start job2 === "+Thread.currentThread());
            System.out.println("print return of job1 === "+res);
            return "第二个任务返回";
        });

    }

    /**
     * 接收上一个任务的返回值作为入参，不会返回任何内容
     * thenAccept
     * thenRun
     */
    @SneakyThrows
    @Test
    public void test04(){
        ForkJoinPool pool=new ForkJoinPool();
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(()->{
            //start job1 === Thread[ForkJoinPool-1-worker-1,5,main]
            System.out.println("start job1 === "+Thread.currentThread());
            return "result of job1";
        },pool);
        CompletableFuture<String> cf2 = cf.thenApply((res) -> {
            System.out.println("start thenApply === " + Thread.currentThread());
            return res + "and then Apply";
        });
        cf2.thenAccept((res)->{//接收上一个任务的执行结果作为入参，但是没有返回值
            System.out.println("start thenAccept == " + Thread.currentThread());
            System.out.println(res+" and the Accpet");
        }).thenRun(()->{
            System.out.println("start thenRun === " + Thread.currentThread());
        });

        System.out.println("主线程 start cf.get()");
        System.out.println(cf.get());
        System.out.println("主线程 end cf.get");
        System.out.println("主线程 start cf2.get()");
        System.out.println(cf2.get());
        System.out.println("主线程 end cf2.get()");


    }

    /**
     * exceptionally方法指定某个任务执行异常时执行的回调方法，会将抛出异常作为参数传递到回调方法中
     */
    @SneakyThrows
    @Test
    public void test05(){
        ForkJoinPool pool=new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+"job1 start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+"job1 exit,time->"+System.currentTimeMillis());
                return 1.2;
            }
        },pool);
        //cf执行异常时，将抛出的异常作为入参传递给回调方法
        CompletableFuture<Double> cf2= cf.exceptionally((param)->{
            System.out.println("start exceptionally === " + Thread.currentThread());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("error stack trace in exceptionally()->");
            param.printStackTrace();
            System.out.println(Thread.currentThread()+" exit,time->"+System.currentTimeMillis());
            return -1.1;
        });
        //cf正常执行时执行的逻辑，如果执行异常则不调用此逻辑
        CompletableFuture cf3=cf.thenAccept((param)->{
            System.out.println("start then Accept === " + Thread.currentThread());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("param->"+param);
            System.out.println("end then Accept === " + Thread.currentThread());
        });

        System.out.println("main thread start,time->"+System.currentTimeMillis());
        //等待子任务执行完成,此处无论是job2和job3都可以实现job2退出，主线程才退出，如果是cf，则主线程不会等待job2执行完成自动退出了
        //cf2.get时，没有异常，但是依然有返回值，就是cf的返回值
        System.out.println("run result->"+cf2.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());
    }

    /**
     * whenComplete  whenComplete是当某个任务执行完成后执行的回调方法，
     * 会将执行结果或者执行期间抛出的异常传递给回调方法，如果是正常执行则异常为null。
     * 回调方法对应的CompletableFuture的result和该任务(whenComplete)一致，如果该任务正常执行，
     * 则get方法返回执行结果，如果是执行异常，则get方法抛出异常。
     *
     * handle handle就是比whenComplete多一个返回值
     */
    @SneakyThrows
    @Test
    public void test06(){
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+"job1 start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+"job1 exit,time->"+System.currentTimeMillis());
                return 1.2;
            }
        });
        //cf执行完成后会将执行结果和执行过程中抛出的异常传入回调方法，如果是正常执行的则传入的异常为null
        CompletableFuture<Double> cf2=cf.whenComplete((a,b)->{
            System.out.println(Thread.currentThread()+"job2 start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(b!=null){
                System.out.println("error stack trace->");
                b.printStackTrace();
            }else{
                System.out.println("run succ,result->"+a);
            }
            System.out.println(Thread.currentThread()+"job2 exit,time->"+System.currentTimeMillis());
        });
        //等待子任务执行完成
        System.out.println("main thread start wait,time->"+System.currentTimeMillis());
        //如果cf是正常执行的，cf2.get的结果就是cf执行的结果
        //如果cf是执行异常，则cf2.get会抛出异常
        System.out.println("run result->"+cf2.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());

    }

    /**
     *  thenCombine / thenAcceptBoth / runAfterBoth
     */
    @SneakyThrows
    @Test
    public void test07(){

    }

    /**
     * thenCompose
     */
    @SneakyThrows
    @Test
    public void test08(){
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(()->{
            System.out.println("doing task1");
            return "res";
        });
        CompletableFuture<String> cf2 = cf.thenCompose((param) -> {
            System.out.println("doing then Compose");
            return CompletableFuture.supplyAsync(() -> {
                return "res in thenCompose";
            });
        });

        System.out.println(cf.get());
        System.out.println(cf2.get());
    }


    @Test
    @SneakyThrows
    public void test09(){
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(()->{
            System.out.println("doing task1");
            return "task1";
        });
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(()->{
            System.out.println("doing task2");
            return "task2";
        });
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(()->{
            System.out.println("doing task3");
            return "task3";
        });
        CompletableFuture<String> cf4 = CompletableFuture.supplyAsync(()->{
            System.out.println("doing task4");
            if(true){
                throw new RuntimeException("test");
            }
            return "task1";
        });
        //allof等待所有任务执行完成才执行cf5，如果有一个任务异常终止，则cf5.get时会抛出异常，都是正常执行，cf4.get返回null
        CompletableFuture<Void> cf5 = CompletableFuture.allOf(cf1, cf2, cf3, cf4).whenComplete((resOfAll,e) -> {
            if(e!=null){
                System.out.println("error stack trace->");
                e.printStackTrace();
            }else{
                System.out.println("run succ,result->"+resOfAll);
            }
        });
        System.out.println(cf5.get());


    }

}
