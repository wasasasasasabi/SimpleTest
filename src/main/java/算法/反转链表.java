package 算法;

import java.util.Objects;

public class 反转链表 {

    public static void main(String[] args) {
        ListNode listNodeHead = ListNode.getListNodeHead(3);
        ListNode listNode = reverseList(listNodeHead);
        System.out.println(1);
    }

    public static ListNode reverseList(ListNode head) {

        ListNode pre = head;
        ListNode node = pre.next;
        head.next = null;
        ListNode next = null;
        while (node.next != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        node.next = pre;
        return node;
    }

    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode headNext = head.next;
        head.next = null;
        return doReverse(headNext, head);
    }

    public static ListNode doReverse(ListNode node, ListNode pre) {
        ListNode tail = null;
        ListNode next = node.next;
        if (Objects.nonNull(node.next)) {
            node.next = pre;
            tail = doReverse(next, node);
        } else {
            node.next = pre;
            return node;
        }
        return tail;
    }
}
