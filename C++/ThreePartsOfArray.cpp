/** http://codeforces.com/contest/1006/problem/C
    idea: two pointer
 */

 #include<iostream>
 #include<vector>

 using namespace std;

typedef long long ll;

int main() {
    int n;
    cin >> n;
    vector<int> arr(n);
    int tmp;
    for (int i=0; i < n; i++) {
        cin >> tmp;
        arr[i] = tmp;
    }
    
    ll sum_left = 0;
    ll sum_right = 0;
    ll sum = 0;
    int l=0;
    int r=n-1;
    bool leftFlg = true;
    bool rightFlg = true;
    while(l < r) {
        if (leftFlg) {
            sum_left += arr[l];
            leftFlg = false;
        }
        if (rightFlg) {
            sum_right += arr[r];
            rightFlg = false;
        }
        if (sum_left == sum_right) {
            sum = sum_left;
            l++;
            r--;
            leftFlg = true;
            rightFlg = true;
        } else if (sum_left > sum_right) {
            r--;
            rightFlg = true;
        } else {
            l++;
            leftFlg = true;
        }
    }
    cout << sum << endl;
    return 0;
}