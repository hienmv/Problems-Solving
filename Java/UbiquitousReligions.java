/** https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1524 #dsu */
import java.util.Scanner;

class UbiquitousReligions {

  public static void makeSet(int[] parent, int[] rank) {
    for (int i = 0; i < parent.length; i++) {
      parent[i] = i;
      rank[i] = 0;
    }
  }

  public static int findSet(int u, int[] parent) {
    if (parent[u] != u) {
      parent[u] = findSet(parent[u], parent);
    }
    return parent[u];
  }

  public static boolean unionSet(int u, int v, int[] parent, int[] rank) {
    int up = findSet(u, parent);
    int vp = findSet(v, parent);

    if (up == vp) {
      return false;
    }

    if (rank[up] > rank[vp]) {
      parent[vp] = up;
    } else if (rank[up] < rank[vp]) {
      parent[up] = vp;
    } else {
      parent[up] = vp;
      rank[vp] += 1;
    }

    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcase = 1;

    while (true) {
      int n = sc.nextInt();
      int m = sc.nextInt();
      if (n == 0 && m == 0) break;

      int[] parent = new int[n + 1];
      int[] rank = new int[n + 1];
      makeSet(parent, rank);

      int u, v;
      int ans = n;
      for (int i = 0; i < m; i++) {
        u = sc.nextInt();
        v = sc.nextInt();
        if (unionSet(u, v, parent, rank)) {
          ans -= 1;
        }
      }
      System.out.println("Case " + testcase + ": " + Math.max(ans, 1));
      testcase++;
    }
  }
}
