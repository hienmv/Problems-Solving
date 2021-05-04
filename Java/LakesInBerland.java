/* https://codeforces.com/contest/723/problem/D
 *  #dfs #dsu #greedy
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class LakesInBerland {

  static boolean isValidLakePoint(int row, int col, char[][] graph) {
    if (row < 0 || row > graph.length - 1) return false;
    if (col < 0 || col > graph[0].length - 1) return false;
    if (graph[row][col] == '*') return false;
    return true;
  }

  static int DFS(Point startPoint, char[][] graph, boolean[][] visitedGraph) {
    Deque<Point> queue = new LinkedList<>();
    queue.add(startPoint);
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int count = 0;
    boolean isValid = true;
    visitedGraph[startPoint.x][startPoint.y] = true;

    while (!queue.isEmpty()) {
      Point p = queue.pollLast();
      count++;
      if (p.x == 0 || p.x == graph.length - 1 || p.y == 0 || p.y == graph[0].length - 1) {
        isValid = false;
      }
      for (int i = 0; i < dx.length; i++) {
        Point temp = new Point(p.x + dx[i], p.y + dy[i]);
        if (isValidLakePoint(temp.x, temp.y, graph) && !visitedGraph[temp.x][temp.y]) {
          visitedGraph[temp.x][temp.y] = true;
          queue.addLast(temp);
        }
      }
    }
    if (isValid == false) return -count;
    return count;
  }

  static void fillLand(Point startPoint, char[][] graph) {
    Deque<Point> queue = new LinkedList<>();
    queue.add(startPoint);
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    graph[startPoint.x][startPoint.y] = '*';
    while (!queue.isEmpty()) {
      Point p = queue.pollLast();
      for (int i = 0; i < dx.length; i++) {
        if (isValidLakePoint(p.x + dx[i], p.y + dy[i], graph)) {
          graph[p.x + dx[i]][p.y + dy[i]] = '*';
          queue.addLast(new Point(p.x + dx[i], p.y + dy[i]));
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int k = sc.nextInt();
    char[][] graph = new char[n][m];
    for (int row = 0; row < n; row++) {
      String str = sc.next();
      graph[row] = str.toCharArray();
    }

    boolean[][] visitedGraph = new boolean[graph.length][graph[0].length];

    ArrayList<Lake> lakesResult = new ArrayList<>();
    for (int row = 0; row < graph.length; row++) {
      for (int col = 0; col < graph[0].length; col++) {
        if (isValidLakePoint(row, col, graph) && visitedGraph[row][col] == false) {
          int count = DFS(new Point(row, col), graph, visitedGraph);
          if (count > 0) {
            lakesResult.add(new Lake(new Point(row, col), count));
          }
        }
      }
    }
    Comparator<Lake> cp =
        new Comparator<Lake>() {
          public int compare(Lake l1, Lake l2) {
            if (l1.size != l2.size) {
              return l1.size - l2.size;
            } else {
              if (l1.startPoint.x != l2.startPoint.x) {
                return l1.startPoint.x - l2.startPoint.x;
              } else {
                return l1.startPoint.y - l2.startPoint.y;
              }
            }
          }
        };
    Collections.sort(lakesResult, cp);
    // result
    int result = 0;
    int needFill = lakesResult.size() - k;
    int count = 0;
    for (Lake l : lakesResult) {
      if (count >= needFill) {
        break;
      }
      fillLand(l.startPoint, graph);
      count++;
      result += l.size;
    }
    System.out.println(result);

    for (char[] row : graph) {
      for (char c : row) {
        System.out.print(c);
      }
      System.out.println("");
    }
  }
}

class Point {
  int x;
  int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

class Lake {
  Point startPoint;
  int size;

  Lake(Point s, int si) {
    startPoint = s;
    size = si;
  }
}
