/** https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1415
 *  #binary-search to find the first idx
 */

#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int bsFirst(vector<int>& arr, int val) {
    int left = 0;
    int right = arr.size() - 1;
    int mid;
    while (left <= right) {
        mid = left + (right-left) / 2;
        if ((mid == left || arr[mid-1] < arr[mid]) && arr[mid] == val) {
            return mid;
        } else if (arr[mid] < val) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1;
}

int main() 
{
    int n, q, tmp, idx;
    int cases = 0;
    while (true) {
        cases++;
        cin >> n;
        cin >> q;
        if (n == 0 && q == 0) break;

        vector<int> arr(n);
        for (int i=0; i < n; i++) {
            cin >> tmp;
            arr[i] = tmp;
        }
        sort(arr.begin(), arr.end());

        cout << "CASE# " << cases << ":" << endl;
        for (int i=0; i < q; i++) {
            cin >> tmp;
            idx = bsFirst(arr, tmp);
            if (idx == -1) {
                cout << tmp << " not found" << endl;
            } else {
                cout << tmp << " found at " << (idx+1) << endl;
            }
        }
    }
    return 0;
}