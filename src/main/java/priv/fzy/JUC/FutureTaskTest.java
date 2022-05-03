package priv.fzy.JUC;

import lombok.SneakyThrows;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    private static final ConcurrentHashMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();

    @SneakyThrows
    public static void main(String[] args) {
//        taskCache.putIfAbsent()
    }
}
