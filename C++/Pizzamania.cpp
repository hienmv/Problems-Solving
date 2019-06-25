/** https://www.spoj.com/problems/OPCPIZZA/
 *  idea: binary search
 */
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {

    int t, n, m;
    cin >> t;
    for(int i=0; i < t; i++) {
        cin >> n;
        cin >> m;
        vector<int> arr(n);
        int tmp;
        for(int k=0; k < n; k++) {
            cin >> tmp;
            arr[k] = tmp;
        }
        sort(arr.begin(), arr.end());
        int count = 0;
        int left = 0, right = n-1;
        while (left < right) {
            if (arr[left] + arr[right] == m) {
                count++;
                right--;
                left++;
            } else if (arr[left] + arr[right] > m) {
                right--;
            } else {
                left++;
            }
        }
        cout << count << endl;
    }
}