// https://leetcode.com/problems/divisor-game/
// #math #dynamic-programming
class Solution {
  public boolean divisorGame(int n) {
    /*
        dp[n] = true/false
          true: start with n, the first player always wins
          false: start with n, always loses

        dp[1] = false
        dp[2] = true
        dp[3] = false

          A:
          dp[n] -> x, n % x == 0  dp[n-x].

            => for (1 -> n) {
              x => n % x == 0 && dp[n-x] == false ? => break => true;
            }

            bottom up
    */

    boolean[] dp = new boolean[n + 1];
    dp[1] = false;

    for (int i = 2; i <= n; i++) {
      //
      int limit = i / 2;
      for (int j = 1; j <= limit; j++) {
        if (i % j == 0 && dp[i - j] == false) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[n];
  }
}
