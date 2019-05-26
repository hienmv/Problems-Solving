/* https://codeforces.com/problemset/problem/546/A
* idea: arithmetic progression
*/
#include<iostream>

using namespace std;

int main() {
    int k, n, w;
    cin >> k >> n >> w;

    if (w == 0) {
        cout << 0 << endl;
        return 0;
    }
    int need = w*(w+1)*k / 2;
    int ret = n < need ? (need - n) : 0;
    cout << ret << endl;

    return 0;
}