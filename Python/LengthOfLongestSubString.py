"""

https://leetcode.com/problems/longest-substring-without-repeating-characters/description/#

--> Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. 
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.


"""
class Solution:
    def lengthOfLongestSubstring(self, s):
        """
        O(n)
        """
        hashMap = {}
        maxLength = 0
        subLength = 0
        firstIndex = 0
        for i in range(len(s)):
            if s[i] in hashMap:
                if hashMap.get(s[i]) < firstIndex :
                    subLength += 1
                else:
                    maxLength = max(maxLength, subLength)
                    firstIndex = hashMap.get(s[i]) + 1
                    subLength = i - firstIndex + 1
            else:
                subLength += 1
            hashMap.update({s[i] : i})
                
        return max(subLength, maxLength)
#test
def main():
    str = "dvdfgfd"
    print(Solution().lengthOfLongestSubstring(str))

if __name__ == '__main__':
    main()