/**
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1186
 * #divide-and-conquer
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class TheClosestPairProblem {
  static double MAX_VAL = 10000d;

  // sort by x
  static Comparator<Point> xcomp =
      new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
          return p1.x - p2.x;
        }
      };
  // sort by y
  static Comparator<Point> ycomp =
      new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
          return p1.y - p2.y;
        }
      };

  // distance
  public static double distance(Point p1, Point p2) {
    double x = p1.x - p2.x;
    double y = p1.y - p2.y;
    return Math.sqrt(x * x + y * y);
  }

  public static double stripCloset(
      ArrayList<Point> arr, int left, int right, int mid, double dist_min) {
    Point midPoint = arr.get(mid);
    ArrayList<Point> splittedPointArr = new ArrayList<>();
    for (int i = left; i < right; i++) {
      if (Math.abs(arr.get(i).x - midPoint.x) <= dist_min) {
        splittedPointArr.add(arr.get(i));
      }
    }

    // sort by y
    Collections.sort(splittedPointArr, ycomp);

    double minVal = MAX_VAL;
    int l = splittedPointArr.size();
    for (int i = 0; i < l; i++) {
      for (int j = i + 1;
          j < l && splittedPointArr.get(j).y - splittedPointArr.get(i).y < dist_min;
          j++) {
        double d = distance(splittedPointArr.get(i), splittedPointArr.get(j));
        minVal = Math.min(minVal, d);
      }
    }

    return minVal;
  }

  public static double bruteForce(ArrayList<Point> arr, int left, int right) {
    double min_dist = MAX_VAL;
    for (int i = left; i < right; i++) {
      for (int j = i + 1; j < right; j++) {
        min_dist = Math.min(min_dist, distance(arr.get(i), arr.get(j)));
      }
    }
    return min_dist;
  }

  public static double getMinDimesion(ArrayList<Point> arr, int left, int right) {
    if (right - left <= 3) {
      return bruteForce(arr, left, right);
    }

    int mid = (right + left) / 2;
    double dist_left = getMinDimesion(arr, left, mid);
    double dist_right = getMinDimesion(arr, mid + 1, right);
    double dist_min = Math.min(dist_left, dist_right);

    return Math.min(dist_min, stripCloset(arr, left, right, mid, dist_min));
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      int n = sc.nextInt();
      if (n == 0) break;

      // get input
      ArrayList<Point> arr = new ArrayList<>();
      int x, y;
      for (int i = 0; i < n; i++) {
        x = sc.nextInt();
        y = sc.nextInt();
        arr.add(new Point(x, y));
      }
      // sort by x
      Collections.sort(arr, xcomp);

      double ans = getMinDimesion(arr, 0, n);

      if (ans < MAX_VAL) {
        System.out.println(String.format("%.4f", ans));
      } else {
        System.out.println("INFINITY");
      }
    }
    return;
  }
}

class Point {
  int x, y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
