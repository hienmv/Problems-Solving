/*  https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1594
 * tag: #dijkstra #shortest-path
 */
import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Boombs {
  static boolean isValidPoint(TreeSet<Point> boombsGraph, Point point, int max_rows, int max_cols) {
    if (point.x < 0 || point.x >= max_rows) return false;
    if (point.y < 0 || point.y >= max_cols) return false;
    if (boombsGraph.contains(point)) return false;

    return true;
  }

  static int Dijkstra(
      TreeSet<Point> boombsGraph, Point source, Point destination, int max_rows, int max_cols) {
    int ret = -1;
    int dx[] = {-1, 0, 0, 1};
    int dy[] = {0, -1, 1, 0};

    TreeMap<Point, Integer> distArr = new TreeMap<>();
    distArr.put(source, 0);

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(source, 0));

    while (!pq.isEmpty()) {
      Node node = pq.poll();
      Point point = node.point;
      int dist = node.dist;
      if (distArr.containsKey(point) && dist > distArr.get(point)) {
        continue;
      }
      for (int i = 0; i < dx.length; ++i) {
        Point p = new Point(point.x + dx[i], point.y + dy[i]);
        if (isValidPoint(boombsGraph, p, max_rows, max_cols)) {
          int newdist = dist + 1;
          if (distArr.containsKey(p)) {
            if (newdist < distArr.get(p)) {
              distArr.replace(p, newdist);
              pq.add(new Node(p, newdist));
            }
          } else {
            distArr.put(p, newdist);
            pq.add(new Node(p, newdist));
          }
        }
      }
    }

    if (distArr.containsKey(destination)) {
      ret = distArr.get(destination);
    } else {
      ret = Dijkstra(new TreeSet<Point>(), source, destination, max_rows, max_cols);
    }
    return ret;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      int r = sc.nextInt();
      int c = sc.nextInt();
      if (r == 0 && c == 0) {
        break;
      }

      int numberBoombsRow = sc.nextInt();

      // graph of points at which bombs is set.
      TreeSet<Point> boombsGraph = new TreeSet<>();
      for (int i = 0; i < numberBoombsRow; ++i) {
        int row = sc.nextInt();
        int cols = sc.nextInt();
        for (int cc = 0; cc < cols; cc++) {
          int col = sc.nextInt();
          boombsGraph.add(new Point(row, col));
        }
      }

      int sx = sc.nextInt();
      int sy = sc.nextInt();
      int dx = sc.nextInt();
      int dy = sc.nextInt();
      int ret = Dijkstra(boombsGraph, new Point(sx, sy), new Point(dx, dy), r, c);
      System.out.println(ret);
    }
  }
}

class Point implements Comparable<Point> {
  int x, y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
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
  Point point;
  int dist;

  Node(Point point, int dist) {
    this.point = point;
    this.dist = dist;
  }

  public int compareTo(Node other) {
    return this.dist - other.dist;
  }
}
