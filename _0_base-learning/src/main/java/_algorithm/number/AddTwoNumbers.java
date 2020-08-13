package _algorithm.number;


/**
 * 2. 两数相加
 */

public class AddTwoNumbers {
    public static void main(String[] args) {

        ListNode test = new ListNode(5);
        //test.next = new ListNode(8);
        //test.next.next = new ListNode(3);
        //test.next.next.next = new ListNode(5);
        //System.out.println(getInt(test));
        //System.out.println(fanzhuan(test));

        ListNode t1 = new ListNode(5);
        //t1.next = new ListNode(1);
        //t1.next.next = new ListNode(9);

        System.out.println(addTwoNumbers(test,t1));


    }
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
        public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            return add(l1,l2);
        }
        public static ListNode add(ListNode node1,ListNode node2){
            int carry = 0;
            ListNode dummyHead = new ListNode(0);
            ListNode current = dummyHead;

           while(node1 != null || node2 !=null){
               int x = node1!=null ? node1.val : 0;
               int y = node2!=null ? node2.val : 0;

               int sum = carry + x + y ;

               carry = sum / 10;
               sum = sum % 10;

               current.next = new ListNode(sum);
               current = current.next;

               node1 = node1.next!=null ? node1.next : null;
               node2 = node2.next!=null ? node2.next : null;
           }

           if(carry > 0){
               current.next = new ListNode(carry);
           }
           return dummyHead.next;
        }

        public static ListNode fanzhuan(ListNode origin){
            if(origin.next == null){
                return origin;
            }
            ListNode prefix = origin.next;
            origin.next = null;
            ListNode result = fanzhuan(prefix);
            getLast(result).next = origin;
            return result;
        }

        public static ListNode getLast(ListNode temp ){
            if(temp.next == null ){
                return temp;
            }
            return getLast(temp.next);
        }
}
class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return toString(this);
    }
    public String toString(ListNode temp ){
        if(temp == null){return "";}
        return temp.val + toString(temp.next);
    }
}
