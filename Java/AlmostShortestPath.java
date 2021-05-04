/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3296
 * tag: #dijkstra #shortest-path use dijkstra k times
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class AlmostShortestPath {
  static int max_val = (int) 1e8;

  static int Dijkstra(ArrayList<Node>[] graph, int source, int destination, boolean visitedPath[]) {
    int len = graph.length;
    int[] distArr = new int[len];
    int[] pathArr = new int[len];
    Arrays.fill(distArr, max_val);
    Arrays.fill(pathArr, -1);

    distArr[source] = 0;
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(source, distArr[source]));

    while (!pq.isEmpty()) {
      Node node = pq.poll();
      int id = node.id;
      int w = node.dist;
      if (w > distArr[id] || visitedPath[id]) {
        continue;
      }
      for (Node neighbour : graph[id]) {
        if (!visitedPath[neighbour.id] && (w + neighbour.dist < distArr[neighbour.id])) {
          distArr[neighbour.id] = w + neighbour.dist;
          pathArr[neighbour.id] = id;
          pq.add(new Node(neighbour.id, distArr[neighbour.id]));
        }
      }
    }

    int st = pathArr[destination];
    while (st != -1 && st != source) {
      visitedPath[st] = true;
      st = pathArr[st];
    }
    return distArr[destination] == max_val ? (-1) : distArr[destination];
  }

  public static void main(String[] args) {
    MyScanner sc = new MyScanner(System.in);
    while (true) {
      int n = sc.nextInt();
      int m = sc.nextInt();
      if (n == 0 && m == 0) break;

      int source = sc.nextInt();
      int destination = sc.nextInt();

      ArrayList<Node>[] graph = new ArrayList[n];
      for (int i = 0; i < graph.length; i++) {
        graph[i] = new ArrayList<Node>();
      }
      for (int i = 0; i < m; ++i) {
        int u = sc.nextInt();
        int v = sc.nextInt();
        int p = sc.nextInt();

        graph[u].add(new Node(v, p));
      }
      boolean[] visitedPath = new boolean[n];
      int minimumCost = Dijkstra(graph, source, destination, visitedPath);
      int almostMinimumCost = minimumCost;
      while (minimumCost != -1 && almostMinimumCost == minimumCost) {
        almostMinimumCost = Dijkstra(graph, source, destination, visitedPath);
      }

      System.out.println(almostMinimumCost);
    }
  }
}

class Node implements Comparable<Node> {

  int id;
  int dist;

  Node(int id, int dist) {
    this.id = id;
    this.dist = dist;
  }

  public int compareTo(Node other) {
    return this.dist - other.dist;
  }
}

class MyScanner {
  BufferedReader br;
  StringTokenizer st;

  public MyScanner(InputStream is) {
    br = new BufferedReader(new InputStreamReader(is));
  }

  String next() {
    while (st == null || !st.hasMoreElements()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return st.nextToken();
  }

  int nextInt() {
    return Integer.parseInt(next());
  }

  long nextLong() {
    return Long.parseLong(next());
  }

  double nextDouble() {
    return Double.parseDouble(next());
  }

  String nextLine() {
    String str = "";
    try {
      str = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }
}
