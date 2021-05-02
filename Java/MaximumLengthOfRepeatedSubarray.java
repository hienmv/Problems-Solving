// https://leetcode.com/problems/maximum-length-of-repeated-subarray/
// #array #hash-table #binary-search #dynamic-programming
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
          
  // dp[i][j] = do dai chuoi chung dai nhat ket thuc tai i va j    
  //   if (nums1[i] == nums2[j])
  //     dp[i][j] = 1 + dp[i-1][j-1]
  //   else 
  //     dp[i][j] = 0
  //   }
        int m = nums1.length;
        int n = nums2.length;
        // max length of the common sub array which finish at index i of nums1 and index j of nums2
        int[][] max_length = new int[m][n];
        
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums1[i] == nums2[j]) {
                    if (i > 0 && j > 0) {
                        max_length[i][j] = 1 + max_length[i-1][j-1];
                    } else {
                        max_length[i][j] = 1;
                    }
                    result = Math.max(result, max_length[i][j]);
                } else {
                    max_length[i][j] = 0;
                }
            }
        }
        return result;
    }
}