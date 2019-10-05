/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1168
 * #dsu #todo
 */
import java.util.Scanner;
import java.util.HashSet;

class Forest {
    public static int findSet(int u, int[] parent) {
        if (parent[u] != u) {
            parent[u] = findSet(parent[u], parent);
        }
        return parent[u];
    }
    public static void unionSet(int u, int v, int[] parent, int[] ranks) {
        int up = findSet(u, parent);
        int vp = findSet(v, parent);
        if (up == vp) {
            return;
        }
        if (ranks[up] > ranks[vp]) {
            parent[vp] = up;
        } else if (ranks[up] < ranks[vp]) {
            parent[up] = vp;
        } else {
            parent[up] = vp;
            ranks[vp]++;
        }
    }
    public static void makeSet(int[] parent, int[] ranks) {
        int sz = parent.length;
        for (int i=0; i < sz; i++) {
            parent[i] = i;
            ranks[i] = 0;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = Integer.parseInt(sc.nextLine());
        sc.nextLine();
        int u, v;
        int sz = 101;
        String str;
        for (int t=0; t < testcases; t++) {
            int[] parent = new int[sz];
            int[] ranks = new int[sz];
            makeSet(parent, ranks);
            if (t > 0) {
                System.out.println("");
            }
            while(sc.hasNextLine()) {
                str = sc.nextLine();
                if (str.isEmpty()) break;
                u = Integer.parseInt(str.split(" ")[0]);
                v = Integer.parseInt(str.split(" ")[1]);
                unionSet(u, v, parent, ranks);
            }
            
            HashSet<Integer> hashSet = new HashSet<>();
            for( int i=1; i < sz; i++) {
                if (ranks[i] != 0) {
                    hashSet.add(parent[i]);
                }
            }
            System.out.println(hashSet.size());
        }
    }
}
