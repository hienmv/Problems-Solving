# https://leetcode.com/problems/h-index/
# #hash-table #sorting
class Solution:
    # sort and binary search
    def hIndex(self, citations: List[int]) -> int:
        val = 0
        size = len(citations)
        if size == 0:
            return val
        
        # sort
        citations.sort()
        left = 1
        right = citations[size - 1]
        while(left <= right):
            mid = left + (right - left) // 2
            'Find leftmost item greater than or equal to mid'
            i = bisect_left(citations, mid)
            if size - i >= mid:
                val = mid
                left = mid + 1
            else:
                right = mid - 1
            
        return val