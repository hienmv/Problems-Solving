/** https://codeforces.com/contest/439/problem/B
 *  idea: short the original array
 */

#include<iostream>
#include<algorithm>
using namespace std;

int main() {
	int n;
	int x;
	cin >> n;
	cin >> x;
	int* arr = new int[n];
	memset(arr, 0, n);
	for (int i=0; i < n; i++) {
		cin >> arr[i];
	}
	sort(arr, arr+n);
	long long result = 0;
	for (int i=0; i < n; i++) {
		result += (long long)arr[i] * x;
		if (x > 1) {
			x -= 1;
		}
	}
	cout << result << endl;

	return 0;
}