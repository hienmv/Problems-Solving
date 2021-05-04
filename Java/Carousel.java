/** https://codeforces.com/contest/1328/problem/D */
/**
 * 1 2 3 3 2 3 1 2 1 2 1 1 2 1 2 3 1 2 1 2 1 2 1 2
 *
 * <p>k = 3
 *
 * <p>k = 1, 2, 3
 *
 * <p>n even: k = 1, 2 k = 1 ? arr[i] = arr[i+1] other k = 2 ? => 1, 2, 1, 2...
 *
 * <p>n odd : K = 1, 2, 3
 *
 * <p>=> Find 1 pair (i, i+1) that has same type. => n even.
 *
 * <p>k = 1 ? arr[i] = arr[i+1] other: fill: k = 2 1, 2, 1, 2 .. => OK? => return: else: K = 3 fill:
 * 1 2 1 2 ... 3 (fixed 0-th:1, n-1 th:3)
 */
import java.util.Scanner;

public class Carousel {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int q = sc.nextInt();
    for (int t = 0; t < q; t++) {
      int n = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }
      int[] result = new int[n];
      int k = 1;
      result[0] = k;
      for (int i = 1; i < n; i++) {
        if (arr[i] != arr[i - 1]) {
          k = 2;
          break;
        }
      }

      if (k == 1) {
        for (int i = 0; i < n; i++) {
          result[i] = 1;
        }
      } else { // k > 1
        for (int i = 1; i < n; i++) {
          result[i] = 3 - result[i - 1];
        }

        if (n % 2 != 0 && arr[n - 1] != arr[0] && result[n - 1] == result[0]) {
          // k = 2
          boolean onlyTwoColor = false;
          for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i - 1]) {
              onlyTwoColor = true;
              result[i] = result[i - 1];
              for (int j = i + 1; j < n; j++) {
                result[j] = 3 - result[j - 1];
              }
              break;
            }
          }
          // k = 3
          if (!onlyTwoColor) {
            result[n - 1] = 3;
            k = 3;
          }
        }
      }
      System.out.println(k);
      for (int i = 0; i < n; i++) {
        System.out.print(result[i] + " ");
      }
      System.out.println();
    }
  }
}
