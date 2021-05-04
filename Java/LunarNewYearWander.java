/**
 * https://codeforces.com/problemset/problem/1106/D #dfs #greedy #shortest-path A B 1 [2, 3, 4] [1,
 * 3, 2] [4, 6, 7, 5] 10 10 1 4 6 8 2 5 3 7 9 4 5 6 3 4 8 10 8 9 1 10
 *
 * <p>A B 1 4 10 1 4 10 3 9 1 4 3 10 9 7 1 4 3 7 10 9 1 4 3 7 9 10 8 1 4 3 7 9 8 10 6
 *
 * <p>1 4 3 7 9 8 6 5 2 10
 */
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

;

public class LunarNewYearWander {
  public static void DFS(ArrayList<ArrayList<Integer>> graph, int vertex) {
    int sz = graph.size();
    boolean[] visitedArr = new boolean[sz];
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    visitedArr[vertex] = true;
    pq.add(vertex);

    while (!pq.isEmpty()) {
      int node = pq.poll();
      System.out.print(node + " ");
      for (int neighbour : graph.get(node)) {
        if (!visitedArr[neighbour]) {
          visitedArr[neighbour] = true;
          pq.add(neighbour);
        }
      }
    }
    System.out.println();
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

    int u, v;
    for (int i = 0; i < m; i++) {
      u = sc.nextInt();
      v = sc.nextInt();
      if (u != v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
      }
    }

    DFS(graph, 1);
  }
}
