/* https://codeforces.com/problemset/problem/1107/C
#greedy #sorting #two-pointer
*/

#include<iostream>
#include<vector>
#include<queue>

using namespace std;

int main() {
    int n, k;
    cin >> n >> k;
    vector<int> unitsVector(n);
    
    for (int i=0; i < n; i++) {
        int tmp;
        cin >> tmp;
        unitsVector[i] = tmp;
    }

    string str;
    cin >> str;
    vector<vector<int> > vt('z' - 'a' + 1);
    for (int i=0; i < str.length(); i++) {
        vt[str[i]-'a'].push_back(i);
    }

    long long sum = 0;
    for(auto vtc : vt) {
        int pre_idx = -10;
        priority_queue<int> pq;
        for (int idx = 0; idx < vtc.size(); idx++) {
            if (vtc[idx] != pre_idx+1) {
                int count = 0;
                while (!pq.empty() && count < k) {
                    sum += pq.top();
                    pq.pop();
                    count++;
                }
                pre_idx = vtc[idx];
                pq = priority_queue<int>();
                pq.push(unitsVector[vtc[idx]]);
            } else {
                pre_idx = vtc[idx];
                pq.push(unitsVector[vtc[idx]]);
            }
        }
        int count = 0;
        while (!pq.empty() && count < k) {
            sum += pq.top();
            pq.pop();
            count++;
        }
    }
    cout << sum << endl;

    return 0;
}