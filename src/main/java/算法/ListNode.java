package 算法;

import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static ListNode getListNodeHead(int size){
        if (size<1) return null;
        int i = 1;
        ListNode ln = new ListNode(0);
        ListNode head = ln;
        while(i<size){
            ListNode curNode = new ListNode(i);
            ln.next = curNode;
            ln=curNode;
            i++;
        }

        return head;
    }
}
