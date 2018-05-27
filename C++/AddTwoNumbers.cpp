#include<iostream>
using namespace std;

/*
 Add Two Numbers - https://leetcode.com/problems/add-two-numbers/description/


You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
*/

// Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* pFirstNode = NULL;
        ListNode* pTempNode = NULL;
        ListNode* pLastNode = NULL;
        int memoryVal = 0;
        int val = 0;
        int sum = 0;   
        
        while(l1 != NULL || l2 != NULL || memoryVal != 0) {
            
            // calculate sum of vals as a val of new Node
            int var1 = 0;
            int var2 = 0;
            if (l1 != NULL) {
                var1 = l1->val;
            }
            if (l2 != NULL) {
                var2 = l2->val;
            }
            
            sum = var1 + var2 + memoryVal;
            
            if (l1 == NULL && l2 == NULL) {
                val = memoryVal;
                memoryVal = 0;
            } else {
                memoryVal = sum / 10;
                val = sum % 10;
            }
            
            // assign result;
            if (pTempNode == NULL){
                pTempNode = new ListNode(val);

                if (pFirstNode == NULL) {
                    pFirstNode = pTempNode;
                }
                if(pLastNode != NULL) {
                   pLastNode->next = pTempNode;
                }
            }
            pLastNode = pTempNode;
            pTempNode = pTempNode->next;
            
            // assign next loop value
            if (l1 != NULL) l1 = l1->next;
            if (l2 != NULL) l2 = l2->next;
        }
        return pFirstNode;
    }

/*  - a shorter method -
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* dummyHead = new ListNode(0);
        ListNode* iterNode = dummyHead;
        int memoryVal = 0;
        int val = 0;
        while (l1 || l2) {
            val = (l1 ? l1->val : 0) + (l2 ? l2->val : 0) + memoryVal;
            memoryVal = val / 10;

            iterNode->next = new ListNode(val % 10);
            iterNode = iterNode->next;

            if (l1) l1 = l1->next;
            if (l2) l2 = l2->next;
        }
        if (memoryVal > 0)
        {
            iterNode->next = new ListNode(memoryVal);
        }

        return dummyHead->next;
    }
*/
};

// no test -- if you want to test, please write testcase yourself or paste this solution in the Leetcode website.
