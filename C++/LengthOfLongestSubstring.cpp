#include<iostream>
#include<map>
using namespace std;

/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/#

--> Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. 
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

// O(n)
int lengthOfLongestSubstring(string s) {
        int maxLength  = 0; // length of longest subString
        int firstIndex = 0; // first index of subString
        int lengthSub = 0;  // length of subString
        
        if(s.empty())
            return 0;
        
        map<char, int> hashMap;
        for(int i = 0; i < s.length(); ++i) {
            map<char, int>::iterator it= hashMap.find(s[i]);
            if (it == hashMap.end()){
                ++lengthSub;
                hashMap.insert(pair<char,int>(s[i],i));
            }else {
                if (it->second < firstIndex) {
                    ++lengthSub;
                } else {
                    maxLength = lengthSub > maxLength ? lengthSub : maxLength;
                    firstIndex = it->second + 1;
                    lengthSub = i - firstIndex + 1;
                }

                it->second = i;
            }
        }

        return lengthSub > maxLength ? lengthSub : maxLength;
    }
//test
int main() {

    string s = "dvdfagd";
    cout << lengthOfLongestSubstring(s) << endl;
    return 0;
}