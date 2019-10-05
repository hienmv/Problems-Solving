/** https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1549
 *  #dsu
 */

import java.util.Scanner;

class Friends {
    public static void makeSet(int[] parent, int[] size) {
        int sz = parent.length;
        for (int i=0; i < sz; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    public static int findSet(int u, int[] parent) {
        if (parent[u] != u) {
            parent[u] = findSet(parent[u], parent);
        }
        return parent[u];
    }
    public static int unionSet(int[] parent, int[] size, int u, int v) {
        int up = findSet(u, parent);
        int vp = findSet(v, parent);
        if (up == vp) {
            return 0;
        }
        if (size[up] > size[vp]) {
            parent[vp] = up;
            size[up] += size[vp];
            return size[up];
        } else if (size[up] < size[vp]) {
            parent[up] = vp;
            size[vp] += size[up];
            return size[vp];
        } else {
            parent[up] = vp;
            size[vp] += size[up];
            return size[vp];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        int n, m, sz, a, b;
        for (int t=0; t < testcase; t++) {
            n = sc.nextInt();
            m = sc.nextInt();
            sz = n+1;
            int[] parent = new int[sz];
            int[] size = new int[sz];
            makeSet(parent, size);
            int ans = 1;
            for (int i=0; i < m; i++) {
                a = sc.nextInt();
                b = sc.nextInt();
                ans = Math.max(ans, unionSet(parent, size, a, b));
            }
            System.out.println(ans);

        }
    }

}