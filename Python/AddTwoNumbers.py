"""
https://leetcode.com/problems/add-two-numbers/description/
#implementation #linked-list #math
"""

class listnode:
    def __init__(self, x):
        self.val = x
        self.next = none

class Solution:
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        carry = 0
        dummyHead = ListNode(0)
        iterNode = dummyHead
        while l1 or l2 or carry:
            if l1:
                carry += l1.val
                l1 = l1.next
            if l2:
                carry +=l2.val
                l2 = l2.next
            
            iterNode.next = ListNode(carry % 10)
            iterNode = iterNode.next
            
            carry //= 10
            
        return dummyHead.next    

        