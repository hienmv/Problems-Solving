/** https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=614
 * idea: use stack
 */
#include<iostream>
#include<string>
#include<deque>

using namespace std;

char validPairChar(char c) {
    if (c == ']') {
        return '[';
    } else if (c == ')') {
        return '(';
    } else {
        return c;
    }
}
string check(string& str) {
    deque<char> stack;
    
    for (string::iterator it = str.begin(); it != str.end(); it++) {
        char c = *it;
#ifdef _BETTER
        if (stack.empty()) {
            stack.push_front(c);
        } else {
            if (c != validPairChar(c)) {
                if (stack.front() != validPairChar(c)) {
                    return "No";
                } else {
                    stack.pop_front();
                }
            } else {
                stack.push_front(c);
            }
        }
#else _BETTER
 // general way
        if (c == '(' || c == '[') 
            stack.push_front(c);
        else { // close
            if (stack.empty() || validPairChar(c) != stack.front()) {
                return "No";
            } else {
                stack.pop_front();
            }
        }
#endif  // _BETTER
    }
    if (!stack.empty()) {
        return "No";
    }
    return "Yes";
}

int main() {
    string str;
    int n;
    cin >> n;
    getline(cin, str);
    string arr[n];
    for (int i=0; i < n; i++) {
        getline(cin, str);
        arr[i] = str;
        
    }
    for(string& str : arr) {
        cout << check(str) << endl;
    }
    return 0;
}