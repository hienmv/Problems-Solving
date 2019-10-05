/* https://www.hackerrank.com/challenges/balanced-brackets/problem
#stack #implementation
*/
// Solution worst O(N) - n: length of string input.

#include<iostream>
#include<stack>
using namespace std;

// check character is closing brackets ? 
bool checkEndCharacter(char input, char &target) {
    target = ' ';
    if (input == ')')
        target = '(';
    else if (input == ']') 
        target = '[';
    else if (input == '}')
        target = '{';

    if (target == ' ')
        return false;
    
    return true;
}

// Idea: if s[i] is closing bracket, 
// check the previous element is corrosponding opening bracket? 
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
            } else {
                st.pop();
            }
        }
    }
    if(!st.empty())
        balanceFlg = false;
        
    return balanceFlg;
}

//test
int main() {
  string s = "([)]";
  
  if(isBalancedBrackets(s))
    cout << "TRUE"<<endl;
  else
    cout << "FALSE"<<endl;
  
  return 0;
}