/**
 * https://codeforces.com/problemset/problem/977/E #dsu #dfs if degree of a vertex is not equal 2 ->
 * not cycle.
 */
/*
    1 -- 2 -- 3
         |    |
         5 -- 4

/**
- DFS : true, false.
    if a degree of a vertex is not equal 2 ->> vertex is invalid
    -> all vertex connecting to it also invalid.]
    ->
*/

/* DSU
import java.util.Scanner;
import java.util.HashSet;

public class CyclicComponents {
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

    public static void unionSet(int[] parent, int[] size, int u, int v) {
        int up = findSet(u, parent);
        int vp = findSet(v, parent);
        if (up == vp) {
            return;
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
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int sz = n+1;
        int[] parent = new int[sz];
        int[] size = new int[sz];
        makeSet(parent, size);
        int[] degree = new int[sz];

        int a, b, ans = 0;
        for (int i=0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            degree[a]++;
            degree[b]++;
            unionSet(parent, size, a, b);
        }
        HashSet<Integer> invalidCycleSet = new HashSet<>();
        for (int i=1; i < sz; i++) {
            if (degree[i] != 2) {
                int p = findSet(i, parent);
                invalidCycleSet.add(p);
            }
        }
        for (int u = 1; u < sz; u++) {
            if (parent[u] == u && !invalidCycleSet.contains(u)) {
                ans += 1;
            }
        }
        System.out.println((ans > 0 ? ans : 0));
    }
}
*/

/** DFS */
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class CyclicComponents {
  public static boolean isValid(int vertex, ArrayList<ArrayList<Integer>> graph) {
    if (graph.get(vertex).size() != 2) return false;
    return true;
  }

  public static boolean DFS(ArrayList<ArrayList<Integer>> graph, int vertex, boolean[] visitedArr) {
    int ret = 0;
    if (!isValid(vertex, graph)) {
      ret = -1;
    }
    visitedArr[vertex] = true;

    Deque<Integer> dq = new LinkedList<>();
    dq.addLast(vertex);
    int node;
    while (!dq.isEmpty()) {
      node = dq.pollLast();
      for (int neighbour : graph.get(node)) {
        if (!isValid(neighbour, graph)) {
          ret = -1;
        }
        if (!visitedArr[neighbour]) {
          visitedArr[neighbour] = true;
          dq.addLast(neighbour);
        } else {
          if (ret == 0) {
            ret = 1;
          }
        }
      }
    }

    return (ret == 1);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int sz = n + 1;
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < sz; i++) {
      graph.add(new ArrayList<Integer>());
    }

    int a, b;
    for (int i = 0; i < m; i++) {
      a = sc.nextInt();
      b = sc.nextInt();
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    int ans = 0;
    boolean[] visitedArr = new boolean[sz];

    for (int i = 1; i < sz; i++) {
      if (!visitedArr[i]) {
        if (DFS(graph, i, visitedArr)) {
          ans += 1;
        }
      }
    }
    System.out.println(ans);
  }
}
