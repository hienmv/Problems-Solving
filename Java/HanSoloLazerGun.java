/*  https://codeforces.com/problemset/problem/514/B
#brute-force #geometry #math #binary-search
*  Binary Search Tree with the formular factor
*
other way: characteristic of 3 point in a line.
(average line)

A (xA, yA);
B (xB, yB);
C (xC, yC);

(xB - xA)*(yC - yA) = (xC - xA)*(yB - yA)

(xB - x0)/(yB - y0) = (xC - x0) / (yC - y0)

A (0, 0)
B (2, 1)
C (10, 5)
*/

import java.util.Scanner;
import java.util.TreeSet;

public class HanSoloLazerGun {

  public static int getGDC(int val0, int val1, int val2) {
    int v1, v2, v0 = Math.min(val0, val1);
    if (val2 != 0) {
      v0 = Math.min(v0, val2);
    }
    if (val0 == v0) {
      v1 = val1;
      v2 = val2;
    } else if (val1 == v0) {
      v1 = val0;
      v2 = val2;
    } else {
      v1 = val0;
      v2 = val1;
    }
    int tmp, gdc = 1;
    for (int i = 1; i <= Math.sqrt(v0); i++) {
      if (v0 % i == 0) {
        tmp = v0 / i;
        if (v1 % tmp == 0 && v2 % tmp == 0) {
          gdc = tmp;
          break;
        }
        if (v1 % i == 0 && v2 % i == 0) {
          gdc = i;
        }
      }
    }
    return gdc;
  }

  public static Formular getFormular(int x1, int y1, int x2, int y2) {
    int a, b, c;
    int gdc = 1;
    // x = k
    if (x1 == x2) {
      b = 0;
      a = 1;
      c = -x1;
    }
    // y = k
    else if (y1 == y2) {
      a = 0;
      b = 1;
      c = -y1;
    }
    // ax + by + c = 0
    else {
      a = y2 - y1;
      b = x1 - x2;
      c = -(a * x1 + b * y1);
      if (a < 0) {
        a = -a;
        b = -b;
        c = -c;
      }
      gdc = getGDC(a, Math.abs(b), Math.abs(c));
    }

    return new Formular(a / gdc, b / gdc, c / gdc);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int x0 = sc.nextInt();
    int y0 = sc.nextInt();
    int x, y;
    TreeSet<Formular> ts = new TreeSet<>();

    for (int i = 0; i < n; i++) {
      x = sc.nextInt();
      y = sc.nextInt();
      if (x == x0 && y == y0) {
        continue;
      }
      Formular f = getFormular(x0, y0, x, y);
      ts.add(f);
    }
    System.out.println(ts.size());
  }
}

// f(x) : ax + by + c = 0;
class Formular implements Comparable<Formular> {
  int a, b, c;

  Formular(int a, int b, int c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public int compareTo(Formular other) {
    if (a == other.a) {
      if (b == other.b) {
        return c - other.c;
      } else {
        return b - other.b;
      }
    } else {
      return a - other.a;
    }
  }
}
