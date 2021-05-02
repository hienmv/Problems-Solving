// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
// #binary-search #heap
class Solution {
    /* other: min-heap */
    /*
    binary-search: try and fails.
    a = [x][y] => the number of numbers that less than a.

    idea:
    with x in range(a[0][0], a[n-1][n-1])
        cnt: elements <= x  : ==> best 0(n) => refactor => functions
    
    if cnt < k:
        increase x
    else: // cnt => k
        res = x
        descrease x
    */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        int target = 0;
        while (left <= right) 
        {
            int mid = left + (right - left) / 2;
            int cnt = getLowerNumbers(matrix, mid);
            if (cnt < k) {
                left = mid + 1;
            }
            else {
                target = mid;
                right = mid - 1;
            }
        }
        return target;    
    }
    
    
    private int getLowerNumbers(int[][] matrix, int x) 
    {
        int n = matrix[0].length;
        int maxY = n - 1, maxX = n;
        int cnt = 0;
        // two pointer with j
        for(int i = 0; i < maxX; i++) {
            int j = maxY;
            while(j >= 0) {
                if (matrix[i][j] <= x) {
                    cnt += j + 1;
                    maxY = j;
                    break;
                }
                j--;
            }
        }
        return cnt;
    }
}