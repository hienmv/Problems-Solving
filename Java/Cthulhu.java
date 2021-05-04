/** https://codeforces.com/problemset/problem/103/B #dfs #dsu */
import java.util.Scanner;

public class Cthulhu {
  public static void makeSet(int[] parent, int[] size) {
    int sz = parent.length;
    for (int i = 0; i < sz; i++) {
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

  public static boolean unionSet(int[] parent, int[] size, int u, int v, int[] cycle) {
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
    int n = sc.nextInt();
    int m = sc.nextInt();
    int sz = n + 1;
    int[] parent = new int[sz];
    int[] size = new int[sz];
    int[] cycle = new int[sz];
    makeSet(parent, size);
    // N == M
    // > 1 -> 1
    // N - 1
    if (n != m) {
      System.out.println("NO");
      return;
    }
    int a, b, ans = n;
    for (int i = 0; i < m; i++) {
      a = sc.nextInt();
      b = sc.nextInt();
      if (unionSet(parent, size, a, b, cycle)) {
        ans -= 1;
      }
    }
    System.out.println(ans == 1 ? "FHTAGN!" : "NO");
    return;
  }
}
