/** https://www.spoj.com/problems/ADFRUITS/ #lcs #dynamic-programming #backtracking */
import java.util.Scanner;

class AdvancedFruits {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String str1 = sc.next();
      String str2 = sc.next();
      // calculate
      int m = str1.length();
      int n = str2.length();
      int[][] L = new int[m + 1][n + 1];
      lcs(str1, m, str2, n, L);
      printResult(str1, m, str2, n, L);
    }
  }

  public static void printResult(String str1, int m, String str2, int n, int[][] L) {
    StringBuilder result = new StringBuilder();
    // edge case
    int len = L[m][n];
    if (len == 0) {
      result.append(str1);
      result.append(str2);
      System.out.println(result);
      return;
    }

    int i = m, j = n;
    int a = m, b = n;
    while (i > 0 && j > 0) {
      if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
        result.insert(0, str1.substring(i, a));
        result.insert(0, str2.substring(j, b));
        result.insert(0, str1.charAt(i - 1));
        i--;
        j--;
        a = i;
        b = j;
      } else if (L[i - 1][j] > L[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }
    result.insert(0, str1.substring(0, a));
    result.insert(0, str2.substring(0, b));
    /*
    while(i > 0 || j > 0) {
        if (i == 0) {
            result.append(str2.charAt(j-1));
            j--;
        }
        else if (j == 0) {
            result.append(str1.charAt(i-1));
            i--;
        }
        else if(str1.charAt(i-1) == str2.charAt(j-1)) {
            result.append(str1.charAt(i-1));
            i--; j--;
        }
        else if (L[i-1][j] > L[i][j-1]) {
            result.append(str1.charAt(i-1));
            i--;
        }
        else {
            result.append(str2.charAt(j-1));
            j--;
        }
    }*/
    System.out.println(result);
  }

  public static void lcs(String str1, int m, String str2, int n, int[][] L) {
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0) {
          L[i][j] = 0;
        } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          L[i][j] = L[i - 1][j - 1] + 1;
        } else {
          L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
        }
      }
    }
  }
}
