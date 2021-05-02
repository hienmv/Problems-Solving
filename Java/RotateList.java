// https://leetcode.com/problems/rotate-list/
// #linked-list #two-pointer
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        
        int len = 0;
        ListNode tmp = head;
        while(tmp != null) {
            len += 1;
            tmp = tmp.next;
        }
        
        int left_len = len - (k % len);
        ListNode left_tail = head;
        while(left_len > 1){
            left_tail = left_tail.next;
            left_len -= 1;
        }
        ListNode new_head = left_tail.next;
        left_tail.next = null;
        ListNode right_tail = new_head;
        if (right_tail != null) {
            while(right_tail.next != null) {
                right_tail = right_tail.next;
            }
            right_tail.next = head;
        } else {
            new_head = head;
        }
        
        return new_head;
    }
}