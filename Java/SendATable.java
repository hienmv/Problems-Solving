/** #number-theory #math */
import java.util.ArrayList;
import java.util.Scanner;

class SendATable {
  static int max = 50000;

  public static ArrayList<Long> getPreCalculatedNum() {
    ArrayList<Long> result = new ArrayList<>();
    result.add(0L); // 0
    result.add(1L); // 1
    long phiVal;
    for (int i = 2; i <= max; i++) {
      phiVal = 2 * phi(i);
      result.add(result.get(i - 1) + phiVal);
    }
    return result;
  }

  public static int phi(int n) {
    int result = n;
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        while (n % i == 0) {
          n /= i;
        }
        result = result / i * (i - 1);
      }
    }
    if (n > 1) {
      result = result / n * (n - 1);
    }
    return result;
  }

  public static void main(String[] args) {
    ArrayList<Long> calculatedArr = getPreCalculatedNum();
    Scanner sc = new Scanner(System.in);
    int n;
    while (true) {
      n = sc.nextInt();
      if (n == 0) break;

      System.out.println(calculatedArr.get(n));
    }
  }
}
