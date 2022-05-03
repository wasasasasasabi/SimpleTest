package 算法;

public class 删除链表的倒数第n个节点 {

    public static void main(String[] args) {
        //初始化链表
        ListNode head = new ListNode(1);
        ListNode tmp = head;
        for (int i = 2; i < 10; i++) {
            tmp.next=new ListNode(i);
            tmp=tmp.next;
        }
        ListNode listNode = removeNthFromEnd(head, 2);
        System.out.println();

    }

    /**
     * 利用快慢指针，快指针先移动n索引，然后快慢指针一起移动。
     * 当快指针移动到end结点的时候，快慢指针相差n位，那么此时慢指针正好是倒数第n个节点
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode slow = head;
        ListNode fast = head;
        int count = 0;
        while (count<n){
            fast=fast.next;
            count++;
        }
        if(fast==null)//说明fast已经移动到end节点了，那删除头节点即可
            return head.next;
        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        slow.next=slow.next.next;
        return head;
    }
}
