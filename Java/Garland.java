/**
 * https://codeforces.com/contest/1287/problem/C #dynamic-programming #greedy #top-down #bottom-up
 * #todo
 */
import java.util.Scanner;

class Garland {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n + 1];
    int oddCnt = 0;
    int evenCnt = 0;
    for (int i = 1; i < n + 1; i++) {
      arr[i] = sc.nextInt();
      if (arr[i] != 0) {
        if (arr[i] % 2 == 0) {
          evenCnt += 1;
        } else {
          oddCnt += 1;
        }
      }
    }
    // count missed numbers
    evenCnt = n / 2 - evenCnt;
    oddCnt = n / 2 - oddCnt;
    if (n % 2 != 0) {
      oddCnt += 1;
    }
    int[][] L = new int[n + 1][2];

    // L[i][even][odd][m] min complexity length i, remain even, odd, modulo m

    // L[n][0][0][0] /
    for (int i = 1; i <= n; i++) {
      if (arr[i] != 0) {
        if (i != 1) {
          if ((arr[i] - arr[i - 1]) % 2 != 0) {
            L[i][arr[i] % 2] = L[i][arr[i - 1] % 2] + 1;
            L[i][(arr[i] + 1) % 2] = L[i][(arr[i - 1] + 1) % 2];
          } else {
            L[i][0] = L[i - 1][0];
            L[i][1] = L[i - 1][1];
          }
        }
        continue;
      }
      // add removed elements
      if (arr[i - 1] % 2 != 0) { // odd
        if (oddCnt > 0) {
          oddCnt--;
          arr[i] = 1;
          L[i][0] = L[i - 1][0];
          L[i][1] = L[i - 1][1];
        } else {
          evenCnt--;
          L[i][0] = L[i - 1][0] + 1;
          L[i][1] = L[i - 1][1];
        }
      } else { // even
        if (evenCnt > 0) {
          evenCnt--;
          L[i][0] = L[i - 1][0];
          L[i][1] = L[i - 1][1];
        } else {
          oddCnt--;
          L[i][0] = L[i - 1][0];
          L[i][1] = L[i - 1][1] + 1;
        }
      }
    }
    for (int i = 0; i <= n; i++) {
      System.out.println(L[i][0] + ", " + L[i][1]);
    }
  }
}
