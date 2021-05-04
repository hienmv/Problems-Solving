/**
 * http://www.spoj.com/problems/SAMER08D/
 *
 * <p>better version at Java/SAMER08DDNASequences.java #dynamic-programming #lcs
 *
 * <p>k = 3 >= k abcde abcd #todo
 */
import java.util.Scanner;

class DNASequences {
  private static int base = 27;
  private static int MOD = 1000000007;
  private static int MAXN = 1001;
  static long[] mul = new long[MAXN];

  static {
    // preprocess
    mul[0] = 1;
    for (int i = 1; i < MAXN; i++) {
      mul[i] = (mul[i - 1] * base) % MOD;
    }
  }

  private static long calculateHashCode(String s, long[] hashArr) {
    long hashValue = 0;
    int sz = s.length();
    int val;
    for (int i = 0; i < sz; i++) {
      val = s.charAt(i) - 'a' + 1;
      hashValue = (val + (base * hashValue) % MOD) % MOD;
      hashArr[i + 1] = hashValue;
    }
    return hashValue;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      int k = sc.nextInt();
      if (k == 0) break;

      String A = sc.next();
      String B = sc.next();

      long[] hashA = new long[A.length() + 1];
      long[] hashB = new long[B.length() + 1];
      // calculate hashcode
      calculateHashCode(new StringBuilder(A).reverse().toString(), hashA);
      calculateHashCode(new StringBuilder(B).reverse().toString(), hashB);

      int result = lcs(A, B, k, hashA, hashB);
      System.out.println(result);
    }
  }

  private static int lcs(String s1, String s2, int k, long[] hashA, long[] hashB) {
    int m = s1.length();
    int n = s2.length();
    int[][] L = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0) {
          L[i][j] = 0;
          continue;
        }

        if (i >= k && j >= k) {
          int cnt = check(m + 1, n + 1, i, j, hashA, hashB);
          if (cnt >= k) {
            for (int sel = k; sel <= cnt; sel++) {
              L[i][j] = Math.max(L[i][j], L[i - sel][j - sel] + sel);
            }
          }
          // select or not
          L[i][j] = Math.max(L[i][j], Math.max(L[i - 1][j], L[i][j - 1]));
        } else { // not select
          L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
        }
      }
    }

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        System.out.print(L[i][j] + " ");
      }
      System.out.println();
    }

    return L[m][n];
  }

  private static int check(
      int maxLen1, int maxLen2, int endIdx1, int endIdx2, long[] hash1, long[] hash2) {
    int ret = 0;
    int l1 = maxLen1 - endIdx1, l2 = maxLen2 - endIdx2;
    int r = Math.min(endIdx1, endIdx2);
    int l = 0;
    while (l < r) {
      int mid = l + (r - l) / 2;
      int r1 = l1 + mid;
      int r2 = l2 + mid;
      /* hash(l, r) = (hash[r] - ((hash[l - 1] * pw[r - l + 1]) % MOD) + MOD) % MOD */
      long h1 = (hash1[r1] - (hash1[l1 - 1] * mul[r1 - l1 + 1]) % MOD + MOD) % MOD;
      long h2 = (hash2[r2] - (hash2[l2 - 1] * mul[r2 - l2 + 1]) % MOD + MOD) % MOD;
      if (h1 == h2) {
        ret = mid;
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    return ret;
  }
}
