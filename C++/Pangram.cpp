/* https://codeforces.com/problemset/problem/520/A
* #string #implementation use a array[26] character
*/

#include<iostream>
#include<vector>

using namespace std;

int main() {
    int len = 'z' - 'a' + 1;
    int n;
    cin >> n;
    string str;
    cin >> str;

    if(str.length() < len) {
        cout << "NO" << endl;
    } 
    else {
        vector<int> charVt(len);
        for (char c : str) {
            int idx;
            if ('a' <= c && c <= 'z') {
                idx = c - 'a';
            }
            if ('A' <= c && c <= 'Z') {
                idx = c - 'A';
            }
            charVt.at(idx) += 1;
        }
        for (int val : charVt) {
            if (val == 0) {
                cout << "NO" << endl;
                return 0;
            }
        }
        cout << "YES" << endl;
    }
    return 0;
}