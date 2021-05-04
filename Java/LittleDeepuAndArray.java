/** #implementation #segment-tree */
import java.util.Scanner;

class LittleDeepuAndArray {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    int m = sc.nextInt();
    int[] hitArr = new int[m];
    for (int i = 0; i < m; i++) {
      hitArr[i] = sc.nextInt();
    }

    /* case 1: brute force */
    // bruteforce(arr, hitArr);

    /* case 2: use segment tree */
    segmentTree(arr, hitArr);
  }

  private static void bruteforce(int[] arr, int[] hitArr) {
    int n = arr.length;
    int m = hitArr.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (arr[i] > hitArr[j]) {
          arr[i] -= 1;
        }
      }
    }
    // print result
    for (int i = 0; i < n; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  private static double log2(int n) {
    return Math.log(n) / Math.log(2);
  }

  private static void segmentTree(int[] arr, int[] query) {
    int n = arr.length;
    // height of segment tree
    int h = (int) Math.ceil(log2(n));
    // max size of segment tree
    int maxSize = 2 * (int) Math.pow(2, h) - 1;
    int[] segTree = new int[maxSize];
    // build tree
    buildTree(arr, segTree, 0, n - 1, 0);
    int[] lazy = new int[maxSize];

    // query
    for (int i = 0; i < query.length; i++) {
      updateQuery_minRangeLazy(segTree, lazy, 0, n - 1, 0, n - 1, query[i], -1, 0);
    }
    updateQuery_minRangeLazy(segTree, lazy, 0, n - 1, 0, n - 1, Integer.MAX_VALUE, 0, 0);

    // print result
    StringBuilder result = new StringBuilder();
    printResult(segTree, 0, result);
    System.out.println(result.toString());
  }

  private static void buildTree(int[] arr, int[] segTree, int left, int right, int idx) {
    if (left == right) {
      segTree[idx] = arr[left];
      return;
    }
    int mid = left + (right - left) / 2;
    buildTree(arr, segTree, left, mid, 2 * idx + 1);
    buildTree(arr, segTree, mid + 1, right, 2 * idx + 2);
    segTree[idx] = Math.min(segTree[2 * idx + 1], segTree[2 * idx + 2]);
  }

  private static void updateQuery_minRangeLazy(
      int[] segTree,
      int[] lazy,
      int left,
      int right,
      int from,
      int to,
      int val,
      int delta,
      int idx) {
    if (left > right) {
      return;
    }

    // make sure all propagation is done at idx
    if (lazy[idx] != 0) {
      segTree[idx] += lazy[idx];
      if (left != right) { // not a leaf node
        lazy[2 * idx + 1] += lazy[idx];
        lazy[2 * idx + 2] += lazy[idx];
      }
      lazy[idx] = 0;
    }
    // no overlap
    if (from > right || to < left) {
      return;
    }
    // total overlap condition
    if (from <= left && to >= right && segTree[idx] > val) {
      segTree[idx] += delta;
      if (left != right) {
        lazy[2 * idx + 1] += delta;
        lazy[2 * idx + 2] += delta;
      }
      return;
    }

    // a leaf node
    if (left == right) {
      return;
    }

    int mid = left + (right - left) / 2;
    updateQuery_minRangeLazy(segTree, lazy, left, mid, from, to, val, delta, 2 * idx + 1);
    updateQuery_minRangeLazy(segTree, lazy, mid + 1, right, from, to, val, delta, 2 * idx + 2);
    segTree[idx] = Math.min(segTree[2 * idx + 1], segTree[2 * idx + 2]);
  }

  private static void printResult(int[] segTree, int idx, StringBuilder sb) {
    // refactor -> as a min query in a range  with same from - to.

    if (idx >= segTree.length || segTree[idx] == 0) {
      return;
    }
    if (idx * 2 + 1 >= segTree.length || segTree[idx * 2 + 1] == 0) {
      sb.append(segTree[idx] + " ");
      return;
    }

    printResult(segTree, 2 * idx + 1, sb);
    printResult(segTree, 2 * idx + 2, sb);
  }
}
