/**
 * #segment-tree
 */
import java.util.Scanner;
class CircularRMQ {
    private static int INF = 1000000000;
    private static double log2(int n) {
        return Math.log(n) / Math.log(2);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i=0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // height of segment tree
        int h = (int)Math.ceil(log2(n));
        // max size of segment tree
        int sizeTree = 2 * (int)Math.pow(2, h) - 1;
        int[] segmentTree = new int[sizeTree];
        // build segment tree
        buildTree(arr, segmentTree, 0, n - 1, 0);
        int[] lazy = new int[sizeTree];
        // query
        int m = sc.nextInt();
        sc.nextLine();
        for (int q = 0; q < m; q++) {
            String cmdLine = sc.nextLine();
            String[] cmd = cmdLine.split(" ");
            if (cmd.length == 2) { // rmg(lf, rg)
                int lf = Integer.parseInt(cmd[0]);
                int rg = Integer.parseInt(cmd[1]);
                int result = INF;
                if (lf <= rg) {
                    result = minRangeLazy(segmentTree, lazy, 0, n - 1, lf, rg, 0);
                }
                else {
                    int tmp1 = minRangeLazy(segmentTree, lazy, 0, n - 1, lf, n - 1, 0);
                    int tmp2 = minRangeLazy(segmentTree, lazy, 0, n - 1, 0, rg, 0);
                    result = Math.min(tmp1, tmp2);
                }

                System.out.println(result);
            }
            else { // inc(lf, rg, v)
                int lf = Integer.parseInt(cmd[0]);
                int rg = Integer.parseInt(cmd[1]);
                int v = Integer.parseInt(cmd[2]);
                if (lf <= rg) {
                    updateQueryMinRangeLazy(segmentTree, lazy, 0, n - 1, lf, rg, v, 0);
                }
                else {
                    updateQueryMinRangeLazy(segmentTree, lazy, 0, n - 1, lf, n - 1, v, 0);
                    updateQueryMinRangeLazy(segmentTree, lazy, 0, n - 1, 0, rg, v, 0);
                }
            }
        }
    }
    // build segment Tree
    private static void buildTree(int[] arr, int[] segmentTree, int left, int right, int idx) {
        if (left == right) {
            segmentTree[idx] = arr[left];
            return;
        }
        int mid = left + (right - left) / 2;
        buildTree(arr, segmentTree, left, mid, 2 * idx + 1);
        buildTree(arr, segmentTree, mid + 1, right, 2 * idx + 2);
        segmentTree[idx] = Math.min(segmentTree[2 * idx + 1], segmentTree[2 * idx + 2]);
    }
    // update segmentTree with lazy propagation in range
    private static void updateQueryMinRangeLazy(int[] segmentTree, int[] lazy, int left, int right, int from, int to, int delta, int idx) {
        if (left > right) {
            return;
        }
        if (lazy[idx] != 0) {
            segmentTree[idx] += lazy[idx];
            if (left != right) { // not a leaf node
                lazy[2 * idx + 1] += lazy[idx];
                lazy[2 * idx + 2] += lazy[idx];
            }
            lazy[idx] = 0;
        }
        // no overlap condition
        if (from > right || to < left) {
            return;
        }
        // total overlap condition
        if (from <= left && right <= to) {
            segmentTree[idx] += delta;
            if (left != right) {
                lazy[2 * idx + 1] += delta;
                lazy[2 * idx + 2] += delta;
            }
            return;
        }

        // otherwise partial overlap so look both left and right
        int mid = left + (right - left) / 2;
        updateQueryMinRangeLazy(segmentTree, lazy, left, mid, from, to, delta, 2 * idx + 1);
        updateQueryMinRangeLazy(segmentTree, lazy, mid + 1, right, from, to, delta, 2 * idx + 2);
        segmentTree[idx] = Math.min(segmentTree[2 * idx + 1], segmentTree[2 * idx + 2]);
    }
    // get minimum value with lazy propagation in range
    private static int minRangeLazy(int[] segmentTree, int[] lazy, int left, int right, int from, int to, int idx) {
        if (left > right) {
            return INF;
        }
        if (lazy[idx] != 0) {
            segmentTree[idx] += lazy[idx];
            if (left != right) { // not a leaf node
                lazy[2 * idx + 1] += lazy[idx];
                lazy[2 * idx + 2] += lazy[idx];
            }
            lazy[idx] = 0;
        }

        // no overlap
        if (from > right || to < left) {
            return INF;
        }

        // total overlap
        if (from <= left && right <= to) {
            return segmentTree[idx];
        }

        // partial overlap
        int mid = left + (right - left) / 2;
        return Math.min(minRangeLazy(segmentTree, lazy, left, mid, from, to, 2 * idx + 1)
            , minRangeLazy(segmentTree, lazy, mid + 1, right, from, to, 2 * idx + 2));
    }
}