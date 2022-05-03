package priv.fzy.JUC.Executor;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class ExecutorDemo02 {

    @SneakyThrows
    public static void main(String[] args) {
        Executors.newCachedThreadPool();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Object> callable = Executors.callable(() -> {
            System.out.println("runable接口本身返回值是void");
        });
        Future<Object> submit = executor.submit(callable);

        Callable<Object> callable2 = Executors.callable(() -> {
            System.out.println("但如过指定了result，会在callable2执行结束后返回result");
        },"这是result");

        Future<Object> submit1 = executor.submit(callable2);

        System.out.println(submit1.get());
        System.out.println(submit.get());

    }
}
