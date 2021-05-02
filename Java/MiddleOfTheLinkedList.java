// https://leetcode.com/problems/middle-of-the-linked-list/
// #linked-list
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode tmp = head;
        int cnt = 0;
        while(tmp != null) {
            cnt++;
            tmp = tmp.next;
        }
        int pos = cnt / 2;
        ListNode result = head;
        while(pos > 0) {
            result = result.next;
            pos--;
        }
        return result;
    }
}