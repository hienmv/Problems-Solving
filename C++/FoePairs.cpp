/** https://codeforces.com/problemset/problem/652/C
 *  #two-pointer
 *  iterator from last index to first index
 *  number of pairs created from index i depend on number of pairs created from index i+1
 */
#include<iostream>
using namespace std;

int main () {
    // added the two lines below 
    ios_base::sync_with_stdio(false); 
    cin.tie(NULL); 

    int n, m;
    cin >> n >> m;
    int thArr[n+1]; 
    int intervalArr[n+1]; 
    int maxRight = n + 1;
    for (int i=0; i < n+1; i++){
        intervalArr[i] = maxRight;
    }
    for (int i=1; i < n+1 ; i++) {
        int tmp;
        cin >> tmp;
        thArr[tmp] = i;
    }
    for (int i=0; i < m; i++) {
        int l, r;
        cin >> l;
        cin >> r;
        if (thArr[l] > thArr[r]) {
            if (intervalArr[thArr[r]] > thArr[l]) {
                intervalArr[thArr[r]] = thArr[l];
            }
        } else {
            if (intervalArr[thArr[l]] > thArr[r]) {
                intervalArr[thArr[l]] = thArr[r];
            }
        }
    }

    long long sum = 0;
    for (int idx = n ; idx > 0; idx--) {
        maxRight = maxRight > intervalArr[idx] ? intervalArr[idx] : maxRight;
        sum += maxRight - idx;
    }
    cout << sum << endl;
}