/** #segment-tree */
import java.util.Arrays;
import java.util.Scanner;

class IntervalProduct {
  private static double log2(int number) {
    return Math.log(number) / Math.log(2);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      int n = sc.nextInt();
      int k = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
        arr[i] = (arr[i] == 0) ? 0 : ((arr[i] > 0) ? 1 : -1);
      }
      // height of segment tree
      int h = (int) Math.ceil(log2(n));
      int sizeTree = 2 * (int) Math.pow(2, h) - 1;
      int[] segTree = new int[sizeTree];
      // set default value to segment Tree
      Arrays.fill(segTree, 1);
      // build segment Tree
      buildTree(arr, segTree, 0, n - 1, 0);

      // query
      StringBuilder result = new StringBuilder();
      String command;
      int i, j;
      for (int q = 0; q < k; q++) {
        command = sc.next();
        i = sc.nextInt();
        j = sc.nextInt();
        if (command.equals("C")) {
          // update segment Tree at index i
          j = (j == 0) ? 0 : ((j > 0) ? 1 : -1);
          updateQuery(segTree, 0, n - 1, 0, i - 1, j);
        } else { // P
          // get multiplication from i->j inclusive
          int val = multiplicationRange(segTree, 0, n - 1, i - 1, j - 1, 0);
          char c = (val == 0) ? '0' : ((val > 0) ? '+' : '-');
          result.append(c);
        }
      }
      System.out.println(result.toString());
    }
  }
  // build segment Tree
  private static void buildTree(int[] arr, int[] segTree, int left, int right, int idx) {
    if (left == right) {
      segTree[idx] = arr[left];
      return;
    }
    int mid = left + (right - left) / 2;
    buildTree(arr, segTree, left, mid, 2 * idx + 1);
    buildTree(arr, segTree, mid + 1, right, 2 * idx + 2);
    segTree[idx] = segTree[2 * idx + 1] * segTree[2 * idx + 2];
  }
  // update segment Tree
  private static void updateQuery(int[] segTree, int left, int right, int idx, int pos, int val) {
    if (pos < left || pos > right) {
      return;
    }
    if (left == right) {
      segTree[idx] = val;
      return;
    }
    int mid = left + (right - left) / 2;
    if (pos <= mid) {
      updateQuery(segTree, left, mid, 2 * idx + 1, pos, val);
    } else { // pos > mid
      updateQuery(segTree, mid + 1, right, 2 * idx + 2, pos, val);
    }
    segTree[idx] = segTree[2 * idx + 1] * segTree[2 * idx + 2];
  }
  // get multiplication range
  private static int multiplicationRange(
      int[] segTree, int left, int right, int from, int to, int idx) {
    if (from <= left && to >= right) {
      return segTree[idx];
    }
    if (from > right || to < left) {
      return 1;
    }
    int mid = left + (right - left) / 2;
    return multiplicationRange(segTree, left, mid, from, to, 2 * idx + 1)
        * multiplicationRange(segTree, mid + 1, right, from, to, 2 * idx + 2);
  }
}
