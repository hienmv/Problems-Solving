
#!/usr/bin/python
# -*- coding: utf-8 -*-
'''
https://leetcode.com/explore/interview/card/top-interview-questions-medium/113/math/818/

Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
'''

class Solution:
    # Solution 1 - Recursion:Time Complexity : O(log(n)) / Space Complexity : O(log(n))   
    def fastPow(self,x, n):
        if n == 0:
            return 1.0
        half = self.fastPow(x, n // 2)
        if n % 2 == 0:
            return half * half
        else:
            return half * half * x
    
    def myPow1(self, x, n):
        """
        :type x: float
        :type n: int
        :rtype: float
        """
        if x == 0: return 0
        if n < 0:
            x = 1 / x
            n = -n
        return self.fastPow(x,n)

    # Solution 2 - fast Power Interative: Time Complexity: O(log(n)), Space Complexity O(1)
    def myPow2(self, x, n):
    	if x == 0: return 0
    	if n == 1: return 1

    	curr_value = x
    	result = 1

    	while n > 0:
    		if n % 2 == 1:
    			result = result * curr_value
    		curr_value = curr_value * curr_value
    		n //= 2

    	return result
#test
output = Solution()
print(output.myPow1(1.00001, 123456))
print(output.myPow2(1.00001, 123456))