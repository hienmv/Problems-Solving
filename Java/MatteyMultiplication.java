/**
 * https://www.hackerearth.com/practice/basic-programming/bit-manipulation/basics-of-bit-manipulation/practice-problems/algorithm/mattey-multiplication-6/
 * #math #number-theory #bit-manipulation
 */
import java.util.Scanner;

class MatteyMultiplication {
  static long getMaxLeftShiftNum(long n) {
    long ans = 0;
    while ((n >> 1) > 0) {
      ans++;
      n >>= 1;
    }
    return ans;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcases = sc.nextInt();
    long n, m;
    long p, count;
    for (int i = 0; i < testcases; i++) {
      n = sc.nextLong();
      m = sc.nextLong();
      StringBuffer ans = new StringBuffer();
      count = 0;
      long val = 1;
      while (m > 0) {
        p = getMaxLeftShiftNum(m);
        if (count > 0) {
          ans.append(" + ");
        }
        ans.append(String.format("(%d<<%d)", n, p));
        if (p == 0) break;
        m -= val << p;
        count++;
      }
      System.out.println(ans.toString());
    }
    return;
  }
}
