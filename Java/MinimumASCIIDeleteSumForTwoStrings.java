// https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings
// #dynamic-programming
/*
Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

// step 1: find LCS
// step 2: iterate each string 
  -> caculate characters not in LCS.
  
  aaaaazzzz
  zzzzaaaaa
  
  xoa z: 8*z=976
  xoa a: 10*a970
  aaaaa 
  
  dp => LCS: "aa"
  
  S1  = "aaz", i ++  => character, 
  LCS = "aa", index: ++
    -> f
    
    LCS da duyet het r: => ...
    
    
    ** indirect **
    => dp[i][j] => max sum by ASCII of CS at (i,j) - i (S1), j (S2).
    
      => S1[i-1] == S2[j-1]
          => dp[i][j] = (value of cur_character by ASCII) + dp[i-1][j-1]
      else 
        => dp[i][j] = Max(dp[i-1][j], dp[i][j-1])
        
        
      => S1] => sum value - dp[m][n]
      S2




    ** direct ** 
      dp[i][j] = min delete of S1[:i] & S2[:j]
        => i == 0 && j == 0 => dp[i][j] = 0;
        => i == 0
          dp[i][j] = dp[i][j-1] + S2[j];
        => j == 0
          dp[i][j] = dp[i-1][j] + S1[i];
        
        
        => s1[i-1] == s2[j-1]
            => dp[i][j] = dp[i-1][j-1];
        => else 
          dp[i][j] = Min(dp[i][j-1] + S2[j], dp[i-1][j] + S1[i]);
*/
public class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int sum_s1 = 0;
        int sum_s2 = 0; 
        for(char c : s1.toCharArray()) {
          sum_s1 += c;
        }
        for(char c : s2.toCharArray()) {
          sum_s2 += c;
        }
        
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
          for (int j = 0; j <= n; j++) {
            if (i == 0 || j == 0) {
              dp[i][j] = 0;
            } else if (s1.charAt(i-1) == s2.charAt(j-1)) {
              dp[i][j] = s1.charAt(i-1) + dp[i-1][j-1];
            } else {
              dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
          }
        }
        
        return sum_s1 + sum_s2 - 2 * dp[m][n];
    }
    
    
    public int minimumDeleteSumDirect(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
          for (int j = 0; j <= n; j++) {
            if (i == 0 && j == 0) {
              dp[i][j] = 0;
            } else if (i == 0) {
              dp[i][j] = dp[i][j-1] + s2.charAt(j-1);
            } else if (j == 0) {
              dp[i][j] = dp[i-1][j] + s1.charAt(i-1);
            } else if (s1.charAt(i-1) == s2.charAt(j-1)) {
              dp[i][j] = dp[i-1][j-1];
            } else {
              dp[i][j] = Math.min(dp[i-1][j] + s1.charAt(i-1), dp[i][j-1] + s2.charAt(j-1));
            }
          }
        }
        
        return dp[m][n];
    }
}
    