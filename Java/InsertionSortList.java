// https://leetcode.com/problems/insertion-sort-list/
// #linked-list #sorting
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode() {}
 * ListNode(int val) { this.val = val; } ListNode(int val, ListNode next) { this.val = val;
 * this.next = next; } }
 */
class Solution {
  // other: convert to arraylist -> sort -> convert to linkedlist
  public ListNode insertionSortList(ListNode head) {
    // idea: build the reverted linkedlist with order
    ListNode tmp = null;
    ListNode node_itr = head;
    while (node_itr != null) {
      ListNode next_node = node_itr.next;
      // insert last
      if (tmp == null || node_itr.val >= tmp.val) {
        node_itr.next = tmp;
        tmp = node_itr;
      } else {
        // find suitable mid position to insert
        ListNode left_node = tmp;
        ListNode right_node = null;
        while (left_node != null && node_itr.val < left_node.val) {
          right_node = left_node;
          left_node = left_node.next;
        }
        node_itr.next = left_node;
        right_node.next = node_itr;
      }

      node_itr = next_node;
    }
    return revertedList(tmp);
  }

  private ListNode revertedList(ListNode head) {
    ListNode tmp = null;
    ListNode node_itr = head;
    while (node_itr != null) {
      ListNode next_node = node_itr.next;
      node_itr.next = tmp;
      tmp = node_itr;
      node_itr = next_node;
    }
    return tmp;
  }
}
