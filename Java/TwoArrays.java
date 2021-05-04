/**
 * #dynamic-programming #math #todo
 *
 * <p>Solution 1: DP a0 <= a1 <= ... <= am <= bm <= b(m-1) <= ... <= b0 <= b0 >= b1 >= .. >= bm
 *
 * <p>Solution 2: math 1 -- n 1 2 3 x x x | x x x x | x x x x x x x x 2 * m + n-1 (position)
 *
 * <p>-> C(2m+n-1, n-1)
 *
 * <p>Method basic: dp[i][x][y] -- so cach tao duoc 2 day a, b do dai i phan tu cuoi cua a la x va
 * cua b la y
 *
 * <p>m * n * n -> n^2 * m ~= 10^7
 *
 * <p>dp[i+1] <=> dp[i]
 */
import java.util.Scanner;

public class TwoArrays {
  private static long MOD = 1000000007L;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    long[][] L = new long[2 * m][n + 1];
    for (int i = 0; i < 2 * m; i++) {
      long sumTmp = 0;
      for (int j = 1; j <= n; j++) {
        if (i == 0 || j == 1) {
          L[i][j] = 1;
          sumTmp = 1;
          continue;
        }
        /*
        L[i][j] = sum(L[i-1][k]), k = 1 .. j
        L[i][j+1] = sum(L[i-1][k]), k = 1 .. j+1
        */
        L[i][j] = (L[i - 1][j] + sumTmp) % MOD;
        sumTmp = L[i][j];
      }
    }
    long result = 0;
    for (int j = 1; j <= n; j++) {
      result = (result + L[2 * m - 1][j]) % MOD;
    }
    System.out.println(result);
  }
}
