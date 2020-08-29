package _algorithm.list;

import org.junit.Test;

/**
 * 21. 合并两个有序链表
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeTwoLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l2.next,l1);
            return l2;
        }
    }

    @Test
    public void test(){
        ListNode l1 = ListUtils.createListNode(new int[]{1, 2, 4});
        ListNode l2 = ListUtils.createListNode(new int[]{1, 3, 4, 7, 8, 9});
        ListNode rst = mergeTwoLists(l1, l2);
        ListUtils.printListNode(rst);
    }
}
