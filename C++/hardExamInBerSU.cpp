/* https://codeforces.com/contest/1185/problem/C2
#greedy #priority-queue #greedy #math // or use a arr[101] to check 0-> 100
 */
// 2 100
// 100 100

// 100 ptu 10
// 10 ptu 20
// 50 ptu 30

// 1600 -> 1500
// cnt[x]: số phần tử có giá trị X mà đang có

// O(N * log(k) * k) với k <= 100

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
    vector<int> arr(101);
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
                
                if (prev * arr[prev] <= sub) {
                    sub -= prev * arr[prev];
                    count += arr[prev];
                } else {
                    count += sub / prev; 
                    if (sub % prev != 0) {
                        count += 1;
                    }
                    sub = 0;
                }
            }
            for( int vv : v){
                pq.push(vv);
            }
        }
        arr[val] += 1;
        if (arr[val] == 1) {
            pq.push(val);
        }
        cout << count;
        if (i < n-1) {
            cout << " ";
        }
    }
    return 0;
}