"""
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/#
#hash-table #string #slicing-window #two-pointer
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