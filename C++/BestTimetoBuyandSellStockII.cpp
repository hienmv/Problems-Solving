/*  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
#implementation #greedy
*/ 

#include<iostream>
#include<vector>

using namespace std;

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