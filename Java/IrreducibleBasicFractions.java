/** #number-theory */
import java.util.Scanner;

class IrreducibleBasicFractions {
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
    Scanner sc = new Scanner(System.in);
    int n = 0;
    while (true) {
      n = sc.nextInt();
      if (n == 0) break;
      System.out.println(phi(n));
    }
  }
}
