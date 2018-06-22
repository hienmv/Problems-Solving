/*
This problem was asked by Facebook.

Given a string of round, curly, and square open and closing brackets, return whether the brackets are balanced (well-formed).

For example, given the string "([])[]({})", you should return true.

Given the string "([)]" or "((()", you should return false.
*/
// Solution worst O(N) - n: length of string input.

#include<iostream>
#include<stack>
using namespace std;

// check character is closing brackets ? 
bool checkEndCharacter(char input, char &target) {
    bool endflg = false;
    if (input == ')' || input == ']' || input == '}') {
        if (input == ')')
        target = '(';
        else if (input == ']') 
        target = '[';
        else
        target = '{';
        endflg = true;
    }else {
        target = ' ';
    }
    
    return endflg;
}

bool isBalancedBrackets(string s) {
    char openChar = ' ';
    stack<char> st;
    bool balanceFlg = true;

    for(int i = 0; i < s.length(); ++i)
    {
        if(!checkEndCharacter(s[i], openChar)){
        st.push(s[i]);
        }
        else {
        if (st.top() != openChar) {
            balanceFlg = false;
            break;
        } else
            st.pop();
        }
    }
    if(!st.empty())
        balanceFlg = false;
        
    return balanceFlg;
}

//test
int main() {
  string s =  "((()";
  
  if(isBalancedBrackets(s))
    cout << "TRUE"<<endl;
  else
    cout << "FALSE"<<endl;
  
  return 0;
}