/**
 * https://codeforces.com/problemset/problem/1095/F #dsu #mst #greedy note: wrong compare in
 * compareTo. (int , long)
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MakeItConnected {
  public static void makeSet(int[] parent, int[] rank) {
    int sz = parent.length;
    for (int i = 0; i < sz; i++) {
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
    if (rank[up] < rank[vp]) {
      parent[up] = vp;
    } else if (rank[up] > rank[vp]) {
      parent[vp] = up;
    } else {
      parent[vp] = up;
      rank[up] += 1;
    }

    return true;
  }

  public static void main(String[] args) throws IOException {
    MyScanner sc = new MyScanner(System.in);

    long w, tmp;
    int n, m, a, b;

    n = sc.nextInt();
    m = sc.nextInt();

    int sz = n + 1;
    long[] initializationCost = new long[sz];
    int startVertex = 1;
    for (int i = 1; i < sz; i++) {
      tmp = sc.nextLong();
      initializationCost[i] = tmp;
      if (tmp < initializationCost[startVertex]) {
        startVertex = i;
      }
    }

    ArrayList<Edge> arrEdge = new ArrayList<>();

    for (int i = 1; i < sz; i++) {
      if (i != startVertex) {
        arrEdge.add(
            new Edge(i, startVertex, initializationCost[i] + initializationCost[startVertex]));
      }
    }

    for (int i = 0; i < m; i++) {
      a = sc.nextInt();
      b = sc.nextInt();
      w = sc.nextLong();
      if (w < initializationCost[a] + initializationCost[b]) {
        arrEdge.add(new Edge(a, b, w));
      }
    }

    Collections.sort(arrEdge);

    long cost = 0;
    int numSelectedEdge = 0;
    int[] parent = new int[sz];
    int[] rank = new int[sz];
    makeSet(parent, rank);

    for (Edge edge : arrEdge) {
      if (unionSet(edge.u, edge.v, parent, rank)) {
        cost += edge.w;
        numSelectedEdge += 1;
      }
      if (numSelectedEdge == n - 1) {
        break;
      }
    }

    System.out.println(cost);
  }
}

class Edge implements Comparable<Edge> {
  int u, v;
  long w;

  public Edge(int u, int v, long w) {
    this.u = u;
    this.v = v;
    this.w = w;
  }

  public int compareTo(Edge other) {
    if (this.w == other.w) {
      return 0;
    } else if (this.w > other.w) {
      return 1;
    } else {
      return -1;
    }
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
