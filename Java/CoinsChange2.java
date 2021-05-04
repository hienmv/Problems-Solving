class CoinsChange2 {
  // https://leetcode.com/problems/coin-change-2/submissions/
  /* DP bottom up
    [1, 2, 5]
  0  1  2  3
  1  1. 1. 1
  2  1. 2. 2
  3  1. 2. 2
  4  1. 3. 3
  5  1. 3. 4

  // the ways to product amount 0 is always 1
  dp[0][j] = 1;

  // the number of ways of producing amount i with j coins is sum of:
      the number of ways of producing amount i with j-1 coins
      and the number of ways of producing amount i-arr[j] with j coins

  dp[i][j] = dp[i][j-1] + dp[i-arr[j]][j]
  dp[1][1] = dp[1][0] + dp[0][1] = 1
  dp[1][2] = dp[1][1] + dp[1-2][2] = 1
  dp[1][3] = dp[1][2] + dp[1-5][3] = 1
  dp[2][1] = dp[2][0] + dp[2-1][1] = dp[1][1] = 1
  dp[2][2] = dp[2][1] + dp[2-2][2] = 1 + dp[0][2] = 2
  dp[2][3] = dp[2][2] + dp[2-5][3] = 2
  dp[3][1] = dp[3][0] + dp[3-1][1] = 1
  dp[3][2] = dp[3][1] + dp[3-2][2] = 1 + dp[1][2] = 2
  dp[3][3] = dp[3][2] + dp[3-5][3] = 2
  dp[4][1] = dp[4][0] + dp[4-1][1] = 1
  dp[4][2] = dp[4][1] + dp[4-2][2] = 1 + 2 = 3
  dp[4][3] = dp[4][2] + dp[4-5][3] = 3
  dp[5][1] = dp[5][0] + dp[5-1][1] = 1
  dp[5][2] = dp[5][1] + dp[5-2][2] = 1 + 2 = 3
  dp[5][3] = dp[5][2] + dp[5-5][3] = 3 + 1 = 4
  */
  public int change(int amount, int[] coins) {
    int len = coins.length;
    int dp[][] = new int[amount + 1][len + 1];
    for (int i = 0; i <= len; i++) {
      dp[0][i] = 1;
    }

    for (int i = 1; i <= amount; i++) {
      for (int j = 1; j <= len; j++) {
        dp[i][j] = dp[i][j - 1];
        if (i >= coins[j - 1]) {
          dp[i][j] += dp[i - coins[j - 1]][j];
        }
      }
    }
    return dp[amount][len];
  }
}
