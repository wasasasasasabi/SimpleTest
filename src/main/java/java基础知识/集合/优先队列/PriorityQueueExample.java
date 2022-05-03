package java基础知识.集合.优先队列;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class PriorityQueueExample {
    public static Random ran = new Random();
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>(7);//初始容量7,会自动扩容
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            queue.add(new Integer(random.nextInt(100)));
        }
        for (int j = 0; j < 7; j++) {
            Integer in = queue.poll();
            System.out.println("Processing Integer:"+in);
        }

        //自定义比较器
        Queue<Customer> customerQueue = new PriorityQueue<>(7,(c1,c2)->{
            return (int)(c1.getID()-c2.getID());//返回正数c1排c2后面,复数相反
        });
        //往队列里添加Customer
        for (int k = 0; k < 7; k++) {
            int id = random.nextInt(100);
            customerQueue.add(new Customer(id, "Pankaj "+id));
        }

        while (true){
            Customer cust = customerQueue.poll();
            if (cust == null) {
                break;
            }
            System.out.println("Processing customer id is "+cust.getID());
        }
    }

    /**
     * 调试查看如何将新元素放入最小堆中(优先队列)
     * ps:add和offer的区别就是当入队时候时add抛异常offer返回null
     *
     * sifuUp大概逻辑
     * while(k>0){
     *     1.通过右移:(k - 1) >>> 1,找到k的父节点(基于公式parnetNo = (nodeNo-1)/2
     *     2.比较parnet的元素和k的元素大小,如果发现自己比parent小,则交换.自己比parent大,则直接break跳出循环
     * }
     *
     */
    @Test
    public void testOfferElement(){
        Queue<Integer> q = new PriorityQueue<Integer>();
        for (int k = 0; k < 20; k++) {
            int id = ran.nextInt(100);
            q.add(ran.nextInt(100));
        }
        //从这里开始debug查看
        q.offer(ran.nextInt(100));

    }

    /**
     * 调试查看如何将出队列(移除最小堆根节点)
     *
     * remove/poll 失败抛出异常/返回null
     *
     *  首先用最后一个元素替换下标为0的那个元素,之后调用siftDown方法对堆进行调整
     *  然后siftDown会将该元素逐层与左右子节点中叫小的那个元素交换,直到x小于或等于左右子节点中的任意一个
     */
    @Test
    public void testPollElment(){
        Queue<Integer> q = new PriorityQueue<Integer>();
        q.add(-1);
        for (int k = 0; k < 20; k++) {
            int id = ran.nextInt(100);
            q.add(ran.nextInt(100));
        }
        q.poll();
    }
}
