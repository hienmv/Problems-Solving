/** https://www.spoj.com/problems/KOZE/ #bfs */
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class Point {
  int x, y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

class KozeSheep {
  static boolean isValidPoint(int x, int y, char[][] graph, boolean[][] visitedGraph) {
    if (x < 0 || x > graph.length - 1) return false;
    if (y < 0 || y > graph[0].length - 1) return false;
    if (visitedGraph[x][y]) return false;
    if (graph[x][y] == '#') return false;
    return true;
  }

  static void calSheepsWolves(Point p, char[][] graph, boolean[][] visitedGraph, int[] result) {
    Deque<Point> queue = new LinkedList<>();
    queue.add(p);
    visitedGraph[p.x][p.y] = true;

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int sheeps = 0;
    int wolves = 0;
    boolean escapedFlg = false;
    while (!queue.isEmpty()) {
      Point point = queue.pollFirst();
      if (!escapedFlg) {
        if (point.x == 0
            || point.x == graph.length - 1
            || point.y == 0
            || point.y == graph[0].length - 1) {
          escapedFlg = true;
        }
      }
      if (graph[point.x][point.y] == 'k') {
        sheeps++;
      } else if (graph[point.x][point.y] == 'v') {
        wolves++;
      }
      for (int i = 0; i < dx.length; i++) {
        int x = point.x + dx[i];
        int y = point.y + dy[i];
        if (isValidPoint(x, y, graph, visitedGraph)) {
          queue.add(new Point(x, y));
          visitedGraph[x][y] = true;
        }
      }
    }
    if (escapedFlg) {
      result[0] += sheeps;
      result[1] += wolves;
    } else if (sheeps > wolves) {
      result[0] += sheeps;
    } else {
      result[1] += wolves;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int maxRows = sc.nextInt();
    int maxCols = sc.nextInt();

    char[][] graph = new char[maxRows][maxCols];
    for (int i = 0; i < maxRows; i++) {
      String str = sc.next();
      graph[i] = str.toCharArray();
    }

    boolean[][] visitedGraph = new boolean[maxRows][maxCols];
    int[] result = new int[2];
    for (int row = 0; row < maxRows; row++) {
      for (int col = 0; col < maxCols; col++) {
        if (isValidPoint(row, col, graph, visitedGraph)) {
          Point p = new Point(row, col);
          calSheepsWolves(p, graph, visitedGraph, result);
        }
      }
    }
    System.out.println(result[0] + " " + result[1]);
  }
}
