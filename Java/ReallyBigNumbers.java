/**
 * http://codeforces.com/problemset/problem/817/C #binary-search |a = n - sum of digits| < k b = (n
 * - 1) - (sum of digits) => b = a ???
 *
 * <p>12: 12 - 3 11: 11 - 2 10: 10 - 1
 *
 * <p>input
 *
 * <p>|n - sum of digits| >= k => result = input - n + 1;
 *
 * <p>find n = binary search |n - sum of digits| >= k
 */
import java.util.Scanner;

public class ReallyBigNumbers {

  private static long sumOfDigits(long n) {
    long r = 0;
    while (n > 0) {
      r += n % 10;
      n /= 10;
    }
    return r;
  }

  private static long minMeetRequireNumber(long n, long s) {
    long ret = n + 1;
    long l = 0, r = n;
    while (l <= r) {
      long mid = l + (r - l) / 2;
      long sumOfDigits = sumOfDigits(mid);
      if (mid - sumOfDigits >= s) {
        ret = mid;
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long n = sc.nextLong();
    long s = sc.nextLong();
    long result = n - minMeetRequireNumber(n, s) + 1;
    System.out.println(result);
  }
}
