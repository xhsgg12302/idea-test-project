package _algorithm.list;

import org.junit.Test;

public class ListUtils {

    public static void printListNode(ListNode head){
        if(head == null) return;
        System.out.print(head.val + " -> ");
        if(head.next != null){
            printListNode(head.next);
        }else{
            System.out.print("null");
            System.out.println();
        }
    }

    public static ListNode createListNode(int[] origin){
        ListNode head= null ,cur = null;
        for (int i = 0; i < origin.length; i++) {
            if(i == 0){
                head = cur = new ListNode(origin[i]);
            }else{
                cur.next = new ListNode(origin[i]);
                cur = cur.next;
            }
        }
        cur.next = null;
        return head;
    }

    @Test
    public void test(){
        int[] raw = {1,2,3,4,5};
        printListNode(createListNode(raw));
    }
}


class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}