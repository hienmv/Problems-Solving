#include<iostream>
#include<vector>

using namespace std;

/*  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

*/ 

// idea: visualize vector elements to graph.
// every peak immediately following a valley to get maximize the profit
int maxProfit1(vector<int>& prices) {
	int max_profit = 0;
	if (prices.size() < 2)
		return 0;
	for (int i = 0; i < prices.size() - 1; i++) {
		if(prices.at(i) < prices.at(i+1))
			max_profit += prices.at(i+1) - prices.at(i);
	}

	return max_profit;
}

int maxProfit2(vector<int>& prices) {
	int i = 0;
	int valley, peak;
	int max = 0;
	while(i < prices.size() - 1) {

		while(i < prices.size() - 1 && prices.at(i) >= prices.at(i+1)) {
			i++;
		}
		valley = prices.at(i);

		while(i < prices.size() - 1 && prices.at(i) <= prices.at(i+1)) {
			i++;
		}
		peak = prices.at(i);

		
		max += peak - valley;
	}

	return max;
}

int main() {
	std::vector<int> v;
	v.push_back(7);
	v.push_back(3);
	v.push_back(6);
	v.push_back(2);
	v.push_back(5);
	v.push_back(4);

	cout << maxProfit1(v) << endl;
	cout << maxProfit2(v) << endl;
	return 0;
}