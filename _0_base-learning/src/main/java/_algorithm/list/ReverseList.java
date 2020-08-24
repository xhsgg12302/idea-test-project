package _algorithm.list;


import org.junit.Test;

/**
 *
 * 剑指 Offer 24. 反转链表
 *
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 *
 * /**
 *  * Definition for singly-linked list.
 *  * public class ListNode {
 *  *     int val;
 *  *     ListNode next;
 *  *     ListNode(int x) { val = x; }
 *  * }
 *
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return  head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    @Test
    public void test(){
        ListNode head = ListUtils.createListNode(new int[]{1,2,3,4,5});
        ListUtils.printListNode(head);
        ListNode rst = reverseList(head);
        ListUtils.printListNode(rst);
    }
}


