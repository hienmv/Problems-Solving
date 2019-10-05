/** https://codeforces.com/problemset/problem/81/A
 *  #deque #implementation
 */
#include<iostream>
#include<deque>
#include<string>

using namespace std;

int main() {
    string str; 
    getline(cin, str);
    deque<char> dq;
    for (std::string::iterator it = str.begin(); it != str.end(); it++) {
        if (dq.empty()) {
            dq.push_back(*it);
        } else {
            if (dq.back() == *it) {
                dq.pop_back();
            }
            else {
                dq.push_back(*it);
            }
        }
    }
    
    while(!dq.empty()) {
        cout << dq.front();
        dq.pop_front();
    }

    return 0;
}