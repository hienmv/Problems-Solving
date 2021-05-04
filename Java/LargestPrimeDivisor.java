/** #number-theory */
import java.util.Scanner;

class LargestPrimeDivisor {
  public static long largestDivisiblePrime(long n) {
    long result = 0;
    long count = 0;
    for (long i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        while (n % i == 0) {
          n /= i;
        }
        count++;
        result = i;
      }
    }
    if (n > 1) {
      count++;
      result = Math.max(result, n);
    }
    return (count > 1) ? result : (-1);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long n = 0;
    while (true) {
      n = sc.nextLong();
      if (n == 0) break;
      if (n < 0) {
        n *= -1;
      }
      System.out.println(largestDivisiblePrime(n));
    }
  }
}
