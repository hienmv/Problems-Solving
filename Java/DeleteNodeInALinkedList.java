// https://leetcode.com/problems/delete-node-in-a-linked-list/
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
    public void deleteNode(ListNode node) {
        ListNode nextNode = node.next;
        ListNode tail = node;
        while(nextNode != null) {
            node.val = nextNode.val;
            if (nextNode.next == null) {
                tail = node;
                break;
            }
            node = nextNode;
            nextNode = nextNode.next;
        }
        // reset tail
        tail.next = null;
    }
}