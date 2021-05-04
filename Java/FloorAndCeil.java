/**
 * #number-theory
 *
 * <p>other way: mathematic way x = p * |x/k| + q * [x/k]
 *
 * <p>if x % k == 0: => p = k && q = 0 else: a = x / k p = 1 - q = 1 - x + a q = x - a -------------
 * details:
 *
 * <p>if x % k = 0: y = x/k => x = y*k y*k = p * y + q * y
 *
 * <p>else : a = x / k b = a + 1
 *
 * <p>x = p * a + q * (a + 1) = p * a + q * a + q x - q = a * (p + q)
 *
 * <p>+ case 1: x - q = a && p + q = 1 p = 1 - q = 1 - x + a q = x - a
 *
 * <p>+ case 2: x - q = p + q && a = 1
 */
import java.util.ArrayList;
import java.util.Scanner;

class FloorAndCeil {
  public static ArrayList<Integer> extendedEuclid(int a, int b) {
    ArrayList<Integer> result = new ArrayList<>();
    int x1 = 0, y1 = 1;
    int x2 = 1, y2 = 0;
    int q, r;
    int x = 1, y = 0;

    // r = a - q * b
    while (b != 0) {
      q = a / b;
      r = a % b;

      x = x2 - q * x1;
      y = y2 - q * y1;
      x2 = x1;
      y2 = y1;
      x1 = x;
      y1 = y;
      a = b;
      b = r;
    }
    result.add(a);
    result.add(x2);
    result.add(y2);
    return result;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcases = sc.nextInt();
    int x, k, a, b, p, q;
    for (int t = 0; t < testcases; t++) {
      x = sc.nextInt();
      k = sc.nextInt();
      a = x / k;
      b = (x + k - 1) / k;
      // a.p + b.q = x
      ArrayList<Integer> result = extendedEuclid(b, a);
      int gcd = result.get(0);
      q = result.get(1) * (x / gcd);
      p = result.get(2) * (x / gcd);
      System.out.println(p + " " + q);
    }
  }
}
