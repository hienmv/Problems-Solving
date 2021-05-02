# https://leetcode.com/problems/excel-sheet-column-number/
# #math
class Solution:
    def titleToNumber(self, s: str) -> int:
        
        def gap(s1, s2) -> int:
            return ord(s1) - ord(s2) + 1

        val = 0
        size = gap('Z', 'A')
        for i in range(len(s)):
            val = val * size + gap(s[i], 'A')
        return val