/** #number-theory */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class AnagrammaticPrimes {
  public static boolean isPrime(int n) {
    if (n < 2) {
      return false;
    }
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
  // @ https://oeis.org/A003459
  public static boolean checkAnagramaticPrime(int n) {
    boolean ret = true;
    // n = 1111111...
    if (n > 1000) {
      while (n % 10 == 1) {
        n /= 10;
      }
      if (n != 0) ret = false;
    } else {
      char[] digits = Integer.toString(n).toCharArray();
      ArrayList<Integer> permutation = new ArrayList<>();
      String numStr;
      for (int i = 0; i < digits.length; i++) {
        for (int j = 0; j < digits.length; j++) {
          if (i != j) {
            if (digits.length == 3) {
              numStr =
                  String.valueOf(digits[i])
                      + String.valueOf(digits[j])
                      + String.valueOf(digits[3 - i - j]);
            } else {
              numStr = String.valueOf(digits[i]) + String.valueOf(digits[j]);
            }
            permutation.add(Integer.parseInt(numStr));
          }
        }
      }
      for (int x : permutation) {
        if (!isPrime(x)) {
          ret = false;
          break;
        }
      }
    }
    return ret;
  }

  public static ArrayList<Integer> sieveOfEratosthenes(int limit) {
    boolean[] mark = new boolean[limit + 1];
    ArrayList<Integer> primes = new ArrayList<>();
    Arrays.fill(mark, true);
    mark[0] = mark[1] = false;
    for (int i = 2; i * i <= limit; i++) {
      if (mark[i]) {
        for (int j = i * i; j <= limit; j += i) {
          mark[j] = false;
        }
      }
    }
    for (int i = 2; i <= limit; i++) {
      if (mark[i]) {
        primes.add(i);
      }
    }
    return primes;
  }

  public static void segmentedSieve(int left, int right, ArrayList<Integer> primes) {
    boolean[] mark = new boolean[right - left + 1];
    Arrays.fill(mark, true);
    for (int i = 0; i < primes.size() && primes.get(i) <= Math.sqrt(right); i++) {
      int base = (left / primes.get(i)) * primes.get(i);
      if (base < left) {
        base += primes.get(i);
      }
      for (int j = base; j <= right; j += primes.get(i)) {
        if (j != primes.get(i)) {
          mark[j - left] = false;
        }
      }
    }
    int result = 0;
    for (int i = left + 1; i <= right; i++) {
      if (mark[i - left]) {
        // check anagramatic of i is prime or not.
        if (checkAnagramaticPrime(i)) {
          result = i;
          break;
        }
      }
    }
    System.out.println(result);
  }

  public static int getLimits(int n) {
    int limits = 1;
    while (n > 0) {
      limits *= 10;
      n /= 10;
    }
    return limits;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n;
    while (true) {
      n = sc.nextInt();
      if (n == 0) break;

      int right = getLimits(n);
      ArrayList<Integer> primes = sieveOfEratosthenes((int) Math.sqrt(right));
      segmentedSieve(n, right, primes);
    }
  }
}
