/* https://codeforces.com/contest/1185/problem/C1
    idea: greedy, priority_queue;
 */

#include<iostream>
#include<queue>

using namespace std;

int main() {
    int n, m;
    cin >> n >> m;
    int sum = 0;
    int val;
    int count = 0;
    priority_queue<int> pq;
    for (int i=0; i < n; i++) {
        cin >> val;
        sum += val;
        count = 0;
        if (sum > m) {
            int sub = sum - m;
            int prev;
            vector<int> v;
            while (sub > 0) {
                prev = pq.top();
                pq.pop();
                v.push_back(prev);
                sub -= prev;
                count++;
            }
            for( int vv : v){
                pq.push(vv);
            }
        }
        pq.push(val);
        cout << count;
        if (i < n-1) {
            cout << " ";
        }
    }
    return 0;
}