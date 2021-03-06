/**
 * have a an two-demention array. find out 9 seeds that no triple of them can make a 3x3 array.
 * create a list of all Points, and shuffle the list. (random characteristic) for each point in the
 * list, check whether it is valid or not ? ( check it with points that are selected and near it).
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean equals(Point other) {
    return this.x == other.x && this.y == other.y;
  }
}

public class Seeds9Of611 {
  static boolean isInvalidPoint(Point p, HashMap<Integer, ArrayList<Point>> resultMap) {
    int i = p.x - 2;
    if (i < 0) i = 0;
    int count = 0;
    for (; i < p.x + 3; ++i) {
      if (resultMap.containsKey(i)) {
        for (Point point : resultMap.get(i)) {
          if (Math.abs(point.y - p.y) < 3) {
            count++;
          }
        }
        if (count > 1) return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int w = 11;
    int h = 6;
    int maxPointNum = 9;
    // create a list of Points
    ArrayList<Point> pointList = new ArrayList<>(w * h);
    for (int x = 0; x < w; ++x) {
      for (int y = 0; y < h; ++y) {
        pointList.add(new Point(x, y));
      }
    }
    // shuffle the list of Points
    Collections.shuffle(pointList);

    int count = 0;
    HashMap<Integer, ArrayList<Point>> resultMap = new HashMap<>();
    for (Point p : pointList) {
      if (isInvalidPoint(p, resultMap)) {
        continue;
      }
      if (!resultMap.containsKey(p.x)) {
        resultMap.put(p.x, new ArrayList<Point>());
      }
      resultMap.get(p.x).add(p);
      count += 1;
      if (count >= maxPointNum) {
        break;
      }
    }

    // print result
    int[][] graph = new int[w][h];
    for (int key : resultMap.keySet()) {
      for (Point p : resultMap.get(key)) {
        graph[p.x][p.y] = 1;
      }
    }
    for (int[] row : graph) {
      for (int col : row) {
        System.out.print(col + " ");
      }
      System.out.println("");
    }
  }
}
