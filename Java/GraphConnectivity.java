/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=400
 *  #dsu
 */

import java.util.Scanner;

class GraphConnectivity {
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
    public static boolean unionSet(int[] parent, int[] size, int u, int v) {
        int up = findSet(u, parent);
        int vp = findSet(v, parent);
        if (up == vp) {
            return false;
        }
        if (size[up] > size[vp]) {
            parent[vp] = up;
            size[up] += size[vp];
        } else if (size[up] < size[vp]) {
            parent[up] = vp;
            size[vp] += size[up];
        } else {
            parent[up] = vp;
            size[vp] += size[up];
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = Integer.parseInt(sc.nextLine());
        sc.nextLine();
        for (int t=0; t < testCases; t++) {
            if (t > 0) System.out.println();

            String str = sc.nextLine();
            int sz = str.toCharArray()[0] - 'A' + 1;
            int[] parent = new int[sz];
            int[] size = new int[sz];
            makeSet(parent, size);
            
            int a, b;
            int ans = sz;
            while(sc.hasNextLine()) {
                str = sc.nextLine();
                if(str.isEmpty()) break;
                a = str.toCharArray()[0] - 'A';
                b = str.toCharArray()[1] - 'A';
                if (unionSet(parent, size, a, b)) {
                    ans -= 1;
                }
            }
        
            System.out.println(ans);
        }
    }
}