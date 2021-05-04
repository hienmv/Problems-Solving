/**
 * http://www.spoj.com/problems/SAMER08D/
 * https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3299
 * (12147 - DNA Sequences) #dynamic-programming #lcs
 *
 * <p>k = 3 >= k abcde abcd
 */
import java.util.Scanner;

class SAMER08DDNASequences {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      int k = sc.nextInt();
      if (k == 0) break;

      String A = sc.next();
      String B = sc.next();

      int result = lcs(A, B, k);
      System.out.println(result);
    }
  }

  private static int lcs(String s1, String s2, int k) {
    int m = s1.length();
    int n = s2.length();
    // lcs end at i, j
    int[][] L = new int[m + 1][n + 1];
    // segment end at i, j
    int[][] C = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0) {
          L[i][j] = 0;
          C[i][j] = 0;
          continue;
        }

        // A[...i] B[.. j]
        // A[.. i-1] A[i]           B[... j-1] B[j]
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          C[i][j] = C[i - 1][j - 1] + 1;
        } else {
          C[i][j] = 0;
        }

        if (i >= k && j >= k && C[i][j] >= k) {
          for (int sel = k; sel <= C[i][j]; sel++) {
            L[i][j] = Math.max(L[i][j], L[i - sel][j - sel] + sel);
          }
          // can select or not
          L[i][j] = Math.max(L[i][j], Math.max(L[i - 1][j], L[i][j - 1]));
        } else { // not select
          L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
        }
      }
    }
    return L[m][n];
  }
}
