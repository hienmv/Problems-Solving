// https://leetcode.com/problems/linked-list-cycle/
// #linked-list #two-pointer
/**
 * Definition for singly-linked list. class ListNode { int val; ListNode next; ListNode(int x) { val
 * = x; next = null; } }
 */
public class Solution {
  // idea: cycle => 1 thang chay nhanh, 1 thang chay cham.
  // 1, 2: buoc chay du nhu de kiem soat,
  // tren hinh tron, dung nhau la diem thuc.
  // truong hop nay la diem nguyen.
  // buoc chay ma khong dong du vs chu vi dg tron -> khong gap nhau dc.
  // 1, 3 => chu trinh chan, nhung nhay vao vi tri le => k gap nhau dc.
  public boolean hasCycle(ListNode head) {
    ListNode iter1 = head;
    if (iter1 == null) return false;

    ListNode iter2 = iter1.next;
    while (iter2 != null) {
      if (iter2 == iter1) {
        return true;
      }

      iter1 = iter1.next;
      iter2 = iter2.next;

      if (iter2 == null) return false;
      iter2 = iter2.next;
    }

    return false;
  }
}
