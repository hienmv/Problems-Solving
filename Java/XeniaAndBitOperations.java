/**
 * #segment-tree
(1,2,3,4,5,6,7,8,9)
h = 1
1 | 2 = 3
3 | 4 = 7
5 | 6 = 7
7 | 8 = 15
9     = 9
->>> h = 2
      3 ^ 7 
      7 ^ 15
      9
      ---> h = 3
          7 | 15.
          9
              ---> h = 4
                 9 ^ 15 = 6
 */
import java.util.Scanner;
class XeniaAndBitOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // also is height of segment tree.
        int m = sc.nextInt();
        int sz = (int)Math.pow(2, n);
        int[] arr = new int[sz];
        for(int i=0; i < sz; i++) {
            arr[i] = sc.nextInt();
        }
        // max size of segment tree
        int sizeTree = 2 * sz - 1;
        int[] segmentTree = new int[sizeTree];
        // build segment Tree
        buildTree(arr, segmentTree, 0, sz - 1, 0, n);

        // calculate
        int p, b;
        for (int i=0; i < m; i++) {
            p = sc.nextInt();
            b = sc.nextInt();
            // update segment Tree
            updateQuery(arr, segmentTree, 0, sz - 1, 0, n, p - 1, b);

            System.out.println(segmentTree[0]);
        }
    }

    // build segment Tree
    private static void buildTree(int[] arr, int[] segmentTree, int left, int right, int idx, int heightOfTree) {
        if (left == right) {
            segmentTree[idx] = arr[left];
            return;
        }
        int mid = left + (right - left) / 2;
        buildTree(arr, segmentTree, left, mid, 2 * idx + 1, heightOfTree - 1);
        buildTree(arr, segmentTree, mid + 1, right, 2 * idx + 2, heightOfTree - 1);
        if (heightOfTree % 2 == 0) {
            segmentTree[idx] = segmentTree[2 * idx + 1] ^ segmentTree[2 * idx + 2];
        }
        else {
            segmentTree[idx] = segmentTree[2 * idx + 1] | segmentTree[2 * idx + 2];
        }
    }

    // update segmentTree
    private static void updateQuery(int[] arr, int[] segmentTree, int left, int right, int idx, int heightOfTree, int pos, int val) {
        if (pos > right || pos < left) {
            return;
        }
        if (left == right) {
            arr[pos] = val;
            segmentTree[idx] = val;
            return;
        }
        int mid = left + (right - left) / 2;
        if (pos <= mid) {
            updateQuery(arr, segmentTree, left, mid, 2 * idx + 1, heightOfTree - 1, pos, val);
        }
        else { // pos > mid
            updateQuery(arr, segmentTree, mid + 1, right, 2 * idx + 2, heightOfTree - 1, pos, val);
        }
        if (heightOfTree % 2 == 0) {
            segmentTree[idx] = segmentTree[2 * idx + 1] ^ segmentTree[2 * idx + 2];
        }
        else {
            segmentTree[idx] = segmentTree[2 * idx + 1] | segmentTree[2 * idx + 2];
        }
    }
}