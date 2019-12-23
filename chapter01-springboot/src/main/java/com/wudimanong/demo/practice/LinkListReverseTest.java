package com.wudimanong.demo.practice;

/**
 * @author joe
 */
public class LinkListReverseTest {

    public static ListNode reverse(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head.next;
        ListNode p3 = null;
        head.next = null;
        while (p2 != null && p2.next != null) {
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }
        head = p1;
        p3.next = head;
        return p2;
    }

    public static void main(String args[]) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode result = reverse(node1);
    }
}
