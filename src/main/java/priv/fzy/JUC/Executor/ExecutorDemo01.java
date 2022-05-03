package priv.fzy.JUC.Executor;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorDemo01 {


    @SneakyThrows
    public static void main(String[] args) {

        //将callable接口和runable接口的实现类提交给ThreadPoolExecutor 返回future接口实现类
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> submit = executor.submit(() -> {
            Thread.sleep(5000);
            System.out.println("准备返回0");
            return 0;
        });
        Thread.sleep(1000);
        System.out.println("卡住 等返回");
        System.out.println(submit.get());
        executor.shutdown();


    }
}
