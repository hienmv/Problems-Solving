/* https://leetcode.com/problems/longest-substring-without-repeating-characters/description/#
#hash-table #string #slicing-window #two-pointer
*/

#include<iostream>
#include<map>
using namespace std;

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
