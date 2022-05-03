package JVM;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class MyNormalMethod implements MyInterface,MyInterface02{

    public static int i = 1;

//    static {
//        i = 2;
//    }

    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void myMethod() {
        System.out.println("1");
    }

    public static void main(String[] args) {
        Deque q = new ArrayDeque();
        q.add(1);
        q.add(2);
        q.add(3);

        q.remove();
        q.removeLast();
    }
}
