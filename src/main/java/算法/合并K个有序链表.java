package 算法;

import java.util.PriorityQueue;
import java.util.Queue;

public class 合并K个有序链表 {

    /**
     * 从每个链表的第一个元素开始遍历,每次循环都将链表的next节点赋值给头节点,如果next==null则不赋值
     * @param lists k个有序链表的头节点
     * @return
     */
    public ListNode mergeKLists01(ListNode[] lists) {
        int k = lists.length;
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (true) {
            ListNode minNode = null;
            int minPointer = -1;
            for (int i = 0; i < k; i++) {
                if (lists[i] == null) {
                    continue;
                }
                //纵向找到最小的节点
                if (minNode == null || lists[i].val < minNode.val) {
                    minNode = lists[i];
                    minPointer = i;
                }
            }
            if (minPointer == -1) {//说明k==0,直接打破循环返回
                break;
            }
            tail.next = minNode;
            tail = tail.next;
            lists[minPointer] = lists[minPointer].next;
        }
        return dummyHead.next;
    }

    /**
     * 使用优先队列
     * @param lists
     */
    public ListNode mergeKLists02(ListNode[] lists){
        Queue<ListNode> q = new PriorityQueue<>((c1,c2)-> c1.val-c2.val);
        int k = lists.length;
        //先让每个链表的头节点进入最小堆
        for (int i = 0; i < k; i++) {
            if(lists[i]!=null)
                q.offer(lists[0]);
        }

        ListNode dummyNode = new ListNode();
        ListNode tail = dummyNode;
        while (!q.isEmpty()){
            //最小元素出队列
            ListNode minNode = q.poll();
            //                                tail
            //                                 ↓
            //dummyNode -> node1 -> node2 -> minNode
            tail.next=minNode;
            tail=minNode;
            if(minNode.next!=null){//如果某个链表的next为空了,那么直接开始下一次循环.
                q.offer(minNode.next);
            }
        }
        return dummyNode.next;
    }

}
