/** #segment-tree */
import java.util.Scanner;

class CuriousRobinHood {
  private static double log2(int number) {
    return Math.log(number) / Math.log(2);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcase = sc.nextInt();
    for (int t = 1; t <= testcase; t++) {
      int n = sc.nextInt();
      int q = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }
      // height segment tree
      int h = (int) Math.ceil(log2(n));
      // maximum size of segment tree
      int sizetree = 2 * (int) Math.pow(2, h) - 1;
      int[] segtree = new int[sizetree];
      buildTree(arr, segtree, 0, n - 1, 0);

      System.out.println("Case " + t + ":");
      int i, v, j;
      for (int k = 0; k < q; k++) {
        int c = sc.nextInt();
        if (c == 1) {
          i = sc.nextInt();
          // print the element at i
          System.out.println(arr[i]);
          // update val at i
          arr[i] = 0;
          // update segment Tree
          updateQuery(segtree, 0, n - 1, 0, i, arr[i]);
        } else if (c == 2) {
          i = sc.nextInt();
          v = sc.nextInt();
          // update val at i
          arr[i] += v;
          // update segment Tree
          updateQuery(segtree, 0, n - 1, 0, i, arr[i]);
        } else if (c == 3) {
          i = sc.nextInt();
          j = sc.nextInt();
          // get sum range
          int sum = sumRange(segtree, 0, n - 1, i, j, 0);
          System.out.println(sum);
        }
      }
    }
  }

  // build segment tree
  private static void buildTree(int[] arr, int[] segtree, int left, int right, int idx) {
    if (left == right) {
      segtree[idx] = arr[left];
      return;
    }
    int mid = left + (right - left) / 2;
    buildTree(arr, segtree, left, mid, 2 * idx + 1);
    buildTree(arr, segtree, mid + 1, right, 2 * idx + 2);
    segtree[idx] = segtree[2 * idx + 1] + segtree[2 * idx + 2];
  }
  // calculate sum range
  private static int sumRange(int[] segtree, int left, int right, int from, int to, int idx) {
    if (from <= left && to >= right) {
      return segtree[idx];
    }
    if (from > right || to < left) {
      return 0;
    }
    int mid = left + (right - left) / 2;
    return sumRange(segtree, left, mid, from, to, 2 * idx + 1)
        + sumRange(segtree, mid + 1, right, from, to, 2 * idx + 2);
  }
  // update value at pos position in segment tree.
  private static void updateQuery(int[] segtree, int left, int right, int idx, int pos, int val) {
    if (pos < left || pos > right) {
      return;
    }
    if (left == right) {
      segtree[idx] = val;
      return;
    }
    int mid = left + (right - left) / 2;
    if (pos <= mid) {
      updateQuery(segtree, left, mid, 2 * idx + 1, pos, val);
    } else {
      updateQuery(segtree, mid + 1, right, 2 * idx + 2, pos, val);
    }
    segtree[idx] = segtree[2 * idx + 1] + segtree[2 * idx + 2];
  }
}
