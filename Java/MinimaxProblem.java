/**
 * https://codeforces.com/problemset/problem/1288/D
 *
 * <p>1 2 3 4 5 1: 5 0 3 1 2 => 1 0 1 1 1 2: 2 3 0 6 3 => 1 1 0 1 1 9 1 0 3 7 => 1 1 0 1 1 2^m-1
 *
 * <p>2^m m = 8 => 2^8
 *
 * <p>O(N*M + 4^M)
 *
 * <p>log(MAX)*(N*M + 4^M)
 *
 * <p>N = 3e5 * 8 ~= 2.4*10^6 4^M ~= 65 *10^3
 *
 * <p>b = calculate(a_i, a_j) min(b) >= x increase x
 *
 * <p>binary search
 */
import java.util.Scanner;

public class MinimaxProblem {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int max = 0, min = 0;
    int[][] arr = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        arr[i][j] = sc.nextInt();
        if (arr[i][j] > max) {
          max = arr[i][j];
        }
        if (arr[i][j] < min) {
          min = arr[i][j];
        }
      }
    }
    Position result = new Position(0, 0);
    Position seleclted = new Position(-1, -1);
    while (min <= max) {
      int x = min + (max - min) / 2;
      if (acceptable(arr, n, m, x, seleclted)) {
        min = x + 1;
        result.row1 = seleclted.row1;
        result.row2 = seleclted.row2;
      } else {
        max = x - 1;
      }
    }
    System.out.println(result.row1 + " " + result.row2);
  }

  private static boolean acceptable(int[][] arr, int n, int m, int x, Position selected) {
    // bitmask
    int size = 1 << m;
    int[] bitmask = new int[size];
    for (int i = 0; i < n; i++) {
      int p = 1;
      int bit_idx = 0;
      for (int j = 0; j < m; j++) {
        int b = (arr[i][j] >= x) ? 1 : 0;
        bit_idx += b * p;
        p <<= 1;
      }
      bitmask[bit_idx] = i + 1;
    }
    // check 2^M states.
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if ((bitmask[i] > 0) && (bitmask[j] > 0) && ((i | j) == size - 1)) {
          selected.row1 = bitmask[i];
          selected.row2 = bitmask[j];
          return true;
        }
      }
    }
    return false;
  }
}

class Position {
  int row1, row2;

  Position(int row1, int row2) {
    this.row1 = row1;
    this.row2 = row2;
  }
}
