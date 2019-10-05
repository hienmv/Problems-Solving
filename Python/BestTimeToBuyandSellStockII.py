'''
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
#implementation #greedy
'''
class Solution:
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        max_profit = 0
        for i in range(0, len(prices) -1):
            if prices[i] <= prices[i+1]:
                max_profit += prices[i+1] - prices[i]
                    
        return max_profit
    
#test
output = Solution()
print(output.maxProfit([7,1,5,3,6,4]))        