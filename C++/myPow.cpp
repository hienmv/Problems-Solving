#include<iostream>
/*
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
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]
*/

// Solution 1, Time Complexity : O(log(n)) / Space Complexity : O(log(n))
double fastPow(double x, int n) {
	if (n == 0) 
		return 1.0;

	double half = fastPow(x, n /2);
	if (n % 2 == 0)
		return half * half;
	else
		return half * half * x; 

}
double myPow1(double x, int n) {
	if (x == 0)
		return 0;
	if ( n < 0) {
		x  = 1 / x;
		n = -n;
	}
	return fastPow(x,n);
}

// Solution 2, Time Complexity: O(log(n)) / Space Complexity: O(1)
double myPow2(double x, int n) {
	if (x == 0) return 0;
	if (n == 0) return 1;
	if (n < 0) {
		x = 1 / x;
		n = -n;
	}

	double curr_value = x;
	double result = 1;
	for (int i = n; i > 0; i /= 2) {
		if (i % 2 == 1) {
			result = result * curr_value;
		}

		curr_value = curr_value * curr_value;
	}

	return result;
}


// test
int main() {
	double x = 1.000001;
	int n = 123456;
	std::cout << myPow1(x, n) << std::endl; 
	std::cout << myPow2(x,n) << std::endl;
}