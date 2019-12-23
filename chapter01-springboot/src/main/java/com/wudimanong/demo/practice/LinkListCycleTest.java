package com.wudimanong.demo.practice;

/**
 * @author joe
 */

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class LinkListCycleTest {

    public static ListNode detectCycle(ListNode head) {
        //方法3：通过指针进行判断(时间复杂度O(1),空间复杂度O(N))
        if (head == null || head.next == null) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head.next;
        ListNode resultNode = null;
        while (p2 != null && p2.next != null) {
            if (p1 == p2.next) {
                System.out.println("the cycle begins is ->" + p1.val);
                resultNode = p1;
                break;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return resultNode;
    }

    public static void main(String args[]) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        ListNode resultNode = detectCycle(node1);
        System.out.println("cycle begins is ->" + resultNode.val);
    }
}
