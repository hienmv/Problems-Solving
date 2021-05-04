// https://leetcode.com/problems/search-a-2d-matrix-ii/
// #binary-search #divide-and-conquer

class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        /* O(nlogn) -> simple
        int rows = matrix.size();
        if (rows == 0) return false;
        int columns = matrix[0].size();
        
        for(int r = 0; r < rows; r++) {
            int c = 0, max_c = columns - 1;
            while(c <= max_c) {
                int mid = c + (max_c - c) / 2;
                if (matrix[r][mid] == target) {
                    return true;
                }
                if (matrix[r][mid] > target) {
                    max_c = mid - 1;
                } 
                else {
                    c = mid + 1;
                }
            }
        }
        
        return false;
        */
        
        // idea: run from right -> left to limit potential value
        int rows = matrix.size();
        if (rows == 0) return false;
        int columns = matrix[0].size();
        
        int r = 0, c = columns - 1;
        while (r < rows && c >= 0) {
            if (matrix[r][c] == target) {
                return true;
            }
            if (matrix[r][c] > target) {
                c--;
            }
            else {
                r++;
            }
        }
        
        return false;
    }
};