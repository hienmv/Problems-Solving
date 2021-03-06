/**
 * https://codeforces.com/problemset/problem/448/C #divide-and-conquer #dynamic-programming #greedy
 * [L, R]
 *
 * <p>- Stroke vertical (R - L + 1) - Stroke horizontal + find min + decrease h[i] - min + cost: min
 * + D&C
 *
 * <p>50 5 5 6 3
 *
 * <p>n = 5 - - - - | | - | | --------- --------------------- 5 6 7 3 6
 *
 * <p>2 3 4 0 3
 */
import java.util.HashSet;
import java.util.Scanner;

class PaintingFence {
  static long getMinimumStrokes(int[] arr, int left, int right) {
    int minHeight = Integer.MAX_VALUE;
    for (int i = left; i < right; i++) {
      if (arr[i] < minHeight) {
        minHeight = arr[i];
      }
    }
    if (minHeight == Integer.MAX_VALUE) {
      return 0;
    }

    // reduce Height
    long strokeNum = minHeight;
    HashSet<Integer> breakIndexSet = new HashSet<>();
    for (int i = left; i < right; i++) {
      arr[i] -= minHeight;
      if (arr[i] == 0) {
        breakIndexSet.add(i);
      }
    }
    int l = left, r = right;
    for (int breakIndex : breakIndexSet) {
      strokeNum += getMinimumStrokes(arr, l, breakIndex);
      l = breakIndex + 1;
    }
    strokeNum += getMinimumStrokes(arr, l, r);

    return Math.min(strokeNum, right - left);
  }

  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    // calculate
    long numStrokes = getMinimumStrokes(arr, 0, n);
    System.out.println(numStrokes);
  }
}
