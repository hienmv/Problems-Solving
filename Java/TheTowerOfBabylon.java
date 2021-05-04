/** #dynamic-programming #lis */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class TheTowerOfBabylon {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcase = 1;
    while (sc.hasNextInt()) {
      int n = sc.nextInt();
      if (n == 0) break;

      int a, b, c;
      ArrayList<Dimensions> list = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        list.add(new Dimensions(a, b, c));
        list.add(new Dimensions(b, a, c));
        list.add(new Dimensions(a, c, b));
        list.add(new Dimensions(c, a, b));
        list.add(new Dimensions(b, c, a));
        list.add(new Dimensions(c, b, a));
      }
      Collections.sort(list);
      int maxHeight = LIS(list);
      System.out.println("Case " + (testcase++) + ": maximum height = " + maxHeight);
    }
  }

  public static int LIS(ArrayList<Dimensions> arr) {
    int len = 0;
    ArrayList<Integer> result = new ArrayList<>();
    for (int i = 0; i < arr.size(); i++) {
      result.add(arr.get(i).z);
    }
    for (int i = 1; i < arr.size(); i++) {
      for (int j = 0; j < i; j++) {
        Dimensions a = arr.get(i);
        Dimensions b = arr.get(j);
        if (a.x > b.x && a.y > b.y && result.get(i) < result.get(j) + a.z) {
          result.set(i, result.get(j) + a.z);
        }
      }
    }
    for (int i = 0; i < arr.size(); i++) {
      if (len < result.get(i)) {
        len = result.get(i);
      }
    }
    return len;
  }
}

class Dimensions implements Comparable<Dimensions> {
  int x, y, z;

  public Dimensions(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public int compareTo(Dimensions other) {
    if (this.x != other.x) {
      return this.x - other.x;
    }
    if (this.y != other.y) {
      return this.y - other.y;
    }
    return this.z - other.z;
  }
}
