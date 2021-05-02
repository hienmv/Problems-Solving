# https://leetcode.com/problems/pascals-triangle-ii/
# #array
class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        # row n - 1
        # val (i) of row n = val(i-1) of row (n-1) + val(i) of row (n-1)
        size = rowIndex + 1
        list = [1] * size
        previous_list  = [1] * size
        for i in range(2, size):
            for j in range(1, i):
                list[j] = previous_list[j] + previous_list[j - 1]
            previous_list, list = list, previous_list
            #print(list, previous)
        return previous_list