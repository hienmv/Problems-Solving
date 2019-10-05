/*
This problem was asked by Amazon.

Run-length encoding is a fast and simple method of encoding strings.
 The basic idea is to represent repeated successive characters as a single count and character. 
 For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".

Implement run-length encoding and decoding. 
You can assume the string to be encoded have no digits and consists solely of alphabetic characters. 
You can assume the string to be decoded is valid.

#implementation
*/

#include<iostream>
#include<string>
using namespace std;

// encoding
string to_encoding(string s) {
    int count = 1;
    string result = "";
    if (s.empty())
        return s;
    
    for(int i = 0; i < s.length() - 1; ++i) {
        if (s[i] == s[i+1]) {
            ++count;
        } else {
            result +=  to_string(count) + string(1, s[i]);
            count = 1;
        }
    }
    
    // add the last element
    result += to_string(count) + string(1, s[s.length() - 1]);
    
    return result;
}

// sub-decoding : 4A -> AAAA
string sub_decoding(char c, int n) {
    string result = "";
    while(n){
        result += string(1,c);
        --n;
    }
    return result;
}

// decoding
string to_decoding(string s) {
    string result = "";
    int count = 0;
    if (s.empty())
        return s;
    
    for(int i = 0; i < s.length(); ++i) {
        if(isdigit(s[i])) {
            count = count * 10 + stoi(string(1,s[i]));
        }else {
            result += sub_decoding(s[i], count);
            count = 0;
        }
    }
    
    return result;
}

//test
int main( int argc, char ** argv )
{
    string s = "AAAABBBCCDAA";
    cout << s << endl;
    cout << to_encoding(s) << endl;
    cout << to_decoding(to_encoding(s)) <<endl;
    return 0;
}
