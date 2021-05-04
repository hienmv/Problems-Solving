/** #kmp */
import java.util.Scanner;

class FileRecoverTesting {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      int k = sc.nextInt();
      String s = sc.next();
      if (k == -1 && s.equals("*")) break;

      int result = 0;
      int n = s.length();
      if (k >= n) {
        // build prefix array
        int[] prefix = new int[n];
        calculatePrefix(s, prefix);
        int cnt = prefix[n - 1];
        if (cnt == 0) {
          result = k / n;
        } else {
          result = 1 + (k - n) / (n - cnt);
        }
      }
      System.out.println(result);
    }
  }

  public static void calculatePrefix(String p, int[] prefix) {
    prefix[0] = 0;
    int m = p.length();
    int j = 0, i = 1;
    while (i < m) {
      if (p.charAt(i) == p.charAt(j)) {
        j++;
        prefix[i] = j;
        i++;
      } else {
        if (j != 0) {
          j = prefix[j - 1];
        } else {
          prefix[i] = 0;
          i++;
        }
      }
    }
  }
}
