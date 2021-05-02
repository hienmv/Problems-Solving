// https://leetcode.com/problems/is-subsequence/
// #binary-search #dynamic-programming #greedy
class Solution {
    // another way: two pointer
    // DP
    public boolean isSubsequence(String s, String t) {
      int len = t.length();
      if (len == 0) {
         return s.isEmpty();
      }
      int[][] next = new int[len][26];
      // initial
      int last_c = t.charAt(len - 1) - 'a';
      for (int i = 0; i < 26; i++) {
        if (i == last_c) {
          next[len-1][i] = len - 1;
        } else {
          next[len-1][i] = -1;
        }
      }
      
      for (int i = len - 2; i >= 0; i--) {
        char c = t.charAt(i);
        for (int j = 0; j < 26; j++) {
          if (c == 'a' + j) {
            next[i][j] = i;
          } else {
            next[i][j] = next[i+1][j];
          }
        }
      }
      int idx = 0;
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (idx >= next.length) {
            return false;
        }
        if (next[idx][c - 'a'] == -1) {
          return false;
        }
        idx = next[idx][c - 'a'] + 1;
      }
      
      return true;
    }
}

/*
 s = "abc", t = "ahbgdc"
 
s = abc
t = askjdnfgksdfgbksjdnfgksjdngc
O(q * (n + m))
O(q * n + m)

next[i][c]: first character c from index i
  => 
    index = 0;
    for each character c of s:
      if (next[index][c] == -1) return false
      index = next[index][c];
    
  
  for i -> n -> 0:
    next[i][c]:
      c == t[i]:
        next[i][t[i]] = i
      c != t[i]:
        next[i][c] = next[i + 1][c] 
        
        
    
    
    for index = 0; index < n; index++
      for (i:index -> i < n):
        next[index][t[i]] = i;
  
*/