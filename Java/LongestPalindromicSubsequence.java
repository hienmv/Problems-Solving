// https://leetcode.com/problems/longest-palindromic-subsequence/submissions/
// #dynamic-programming #lcs
/*
Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".

// Approach 1: indirect
// s
// s1 = s.reverse()
  => LCS(s, s1)
  
// Approach 2: direct
  dp[i][j] = longest palindrome of S[i..j]
    i -> k => dp[i][k-1] + 2 => (k, j)
    
    
  idea 1: -> work but complicated
    s[j] nam trong S[i...j-1]
      => dp[i][j]  = max of - khong chon: dp[i][j-1],  
                            - chon: 
                                  -> index: k1, k2... <= j-1 ma S[k..] = S[j]
                                    dp[k1 + 1][j - 1] + 2 (chuoi doi xung: k1 ... j)                  
    else
      => dp[i][j] = dp[i][j-1] 
    
  
  idea 2: s[i] == s[j] => ? 
          s[i] != s[j] => >
    

    dp[i][i] = 1
    dp[i][i + 1] = 2 if s[i] == s[i + 1] else 1
        
    (**) => iterate: i desc, j acs; j > i
    s[i] == s[j], j = i + 1
      => dp[i][j] = dp[i+1][j-1] + 2 (**)
    s[i] != s[j]
      => dp[i][j] = Max(dp[i][j-1], dp[i+1][j])
*/

public class LongestPalindromicSubsequence {
  public int longestPalindromeSubseq(String s) {
    int n = s.length(); 
    // dp[i][j] : max length of palindrome subsequence from i -> j,
    int[][] dp = new int[n][n];
    
    // base - note point
    for (int i = 0; i < n; i++) {
      dp[i][i] = 1;
      if (i < n-1) {
        if (s.charAt(i) == s.charAt(i+1)) {
          dp[i][i+1] = 2;
        } else {
          dp[i][i+1] = 1;
        }
      }
    }
    for (int i = n-1; i >= 0; i--) { // note point: the way to iterate
      for (j = i+2; j < n; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          dp[i][j] = dp[i+1][j-1] + 2;
        } else {
          dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
        }
      }
    }
    
    return dp[0][n-1];
  }
}