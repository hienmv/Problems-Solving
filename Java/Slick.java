/** https://www.spoj.com/problems/UCV2013H/ #bfs */
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

class Slick {
  static boolean isValidPoint(int x, int y, int[][] graph, boolean[][] visitedGraph) {
    if (x < 0 || x > graph.length - 1) return false;
    if (y < 0 || y > graph[0].length - 1) return false;
    if (visitedGraph[x][y]) return false;
    if (graph[x][y] == 0) {
      visitedGraph[x][y] = true;
      return false;
    }
    return true;
  }

  static int calReachedPoint(Point startPoint, int[][] graph, boolean[][] visitedGraph) {
    int count = 1;
    Deque<Point> queue = new LinkedList<>();
    queue.addLast(startPoint);
    visitedGraph[startPoint.x][startPoint.y] = true;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    while (!queue.isEmpty()) {
      Point point = queue.pollFirst();
      for (int i = 0; i < dx.length; i++) {
        if (isValidPoint(point.x + dx[i], point.y + dy[i], graph, visitedGraph)) {
          count++;
          visitedGraph[point.x + dx[i]][point.y + dy[i]] = true;
          queue.addLast(new Point(point.x + dx[i], point.y + dy[i]));
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      int maxRows = sc.nextInt();
      int maxCols = sc.nextInt();
      if (maxRows == 0 && maxCols == 0) {
        break;
      }

      int[][] graph = new int[maxRows][maxCols];
      for (int row = 0; row < maxRows; row++) {
        for (int col = 0; col < maxCols; col++) {
          int temp = sc.nextInt();
          graph[row][col] = temp;
        }
      }

      boolean[][] visitedGraph = new boolean[maxRows][maxCols];
      int[] result = new int[maxRows * maxCols + 1];
      int resultCount = 0;
      for (int row = 0; row < maxRows; row++) {
        for (int col = 0; col < maxCols; col++) {
          if (isValidPoint(row, col, graph, visitedGraph)) {
            Point startPoint = new Point(row, col);
            int count = calReachedPoint(startPoint, graph, visitedGraph);
            result[count] += 1;
            resultCount += 1;
          }
        }
      }
      System.out.println(resultCount);
      for (int i = 0; i < result.length; i++) {
        if (result[i] != 0) {
          System.out.println(i + " " + result[i]);
        }
      }
    }
    sc.close();
  }
}
