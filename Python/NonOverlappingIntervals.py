# https://leetcode.com/problems/non-overlapping-intervals/
# #greedy
class Solution:
    class Point:
        def __init__(self, start, end):
            self.start = start
            self.end = end
        def __lt__(self, other):
            if (self.start != other.start):
                return self.start < other.start
            else:
                return self.end < other.end
        def __repr__(self):
            return "{} {}".format(self.start, self.end)
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        l = list()
        for index, (start, end) in enumerate(intervals):
            l.append(self.Point(start, end))
        l.sort()
        if len(l) == 0:
            return 0
        count = 0
        max_overlap_point = l[0]
        print(max_overlap_point)
        for i in range(1, len(l)):
            cur_point = l[i]
            # check interval
            if max_overlap_point.end >= cur_point.end:
                count += 1
                max_overlap_point = cur_point
            elif max_overlap_point.end > cur_point.start:
                count += 1
            else:
                max_overlap_point = cur_point
        return count
        