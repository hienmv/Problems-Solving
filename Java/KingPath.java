/**
 * https://codeforces.com/problemset/problem/242/C #dfs #hash-table #shortest-path
 *
 * <p>use Dijkstra with adjacency matrix other way: use HashMap, HashSet. override equals and
 * hashCode of Point class (hashCode: x*p1 + y*p2 - max_val), p1, p2 is prime number and p1 # p2.
 * weak point: take time to hashCode,.. not better than above way. (time limited at test case 23)
 */
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class KingPath {

  static int max_val = (int) 1e9 + 1;

  static boolean isValidPoint(TreeSet<Point> graph, Point point) {
    if (point.x < 0 || point.x >= max_val) return false;
    if (point.y < 0 || point.y >= max_val) return false;
    if (!graph.contains(point)) return false;
    return true;
  }

  static void Dijkstra(
      TreeSet<Point> graph, TreeMap<Point, Integer> dist, Point source, Point destination) {

    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    dist.put(source, 0);
    Node startVertex = new Node(source, 0);
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(startVertex);

    while (!pq.isEmpty()) {
      Node node = pq.poll();
      Point point = node.p;
      int w = node.dist;
      if (dist.containsKey(point) && w > dist.get(point)) {
        continue;
      }
      for (int i = 0; i < dx.length; i++) {
        Point tmp = new Point(point.x + dx[i], point.y + dy[i]);
        if (isValidPoint(graph, tmp)) {
          if (dist.containsKey(tmp)) {
            if (w + 1 < dist.get(tmp)) {
              dist.replace(tmp, w + 1);
              pq.add(new Node(tmp, dist.get(tmp)));
            }
          } else {
            dist.put(tmp, w + 1);
            pq.add(new Node(tmp, dist.get(tmp)));
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int x0 = sc.nextInt();
    int y0 = sc.nextInt();
    int x1 = sc.nextInt();
    int y1 = sc.nextInt();
    int n = sc.nextInt();
    TreeSet<Point> graph = new TreeSet<>();
    for (int i = 0; i < n; i++) {
      int r = sc.nextInt();
      int a = sc.nextInt();
      int b = sc.nextInt();
      while (a <= b) {
        graph.add(new Point(r, a++));
      }
    }
    TreeMap<Point, Integer> dist = new TreeMap<>();
    Point source = new Point(x0, y0);
    Point destination = new Point(x1, y1);
    Dijkstra(graph, dist, source, destination);

    if (dist.containsKey(destination)) {
      System.out.println(dist.get(destination));
    } else {
      System.out.println(-1);
    }
  }
}

class Point implements Comparable<Point> {
  int x, y;

  Point(int _x, int _y) {
    this.x = _x;
    this.y = _y;
  }

  public int compareTo(Point other) {
    if (this.x == other.x) {
      return this.y - other.y;
    } else {
      return this.x - other.x;
    }
  }
}

class Node implements Comparable<Node> {
  Point p;
  int dist;

  Node(Point _p, int _dist) {
    this.p = _p;
    this.dist = _dist;
  }

  public int compareTo(Node other) {
    return this.dist - other.dist;
  }
}
