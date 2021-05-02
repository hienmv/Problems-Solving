// https://leetcode.com/problems/merge-k-sorted-lists/
// #linked-list #divide-and-conquer #heap
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
    // O(n*log(len(lists)))
    public ListNode mergeKLists(ListNode[] lists) {
        Comparator<ListNode> comparator = (ListNode n1, ListNode n2) -> {
            return n1.val - n2.val;
        };
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>(comparator);
        for(int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }
        ListNode head = new ListNode();
        ListNode tmp = head;
        while(!pq.isEmpty()) {
            ListNode min = pq.remove();
            if (min.next != null) {
                pq.add(min.next);
            }
            tmp.next = new ListNode(min.val);
            tmp = tmp.next;
        }
        
        return head.next;
    }
}