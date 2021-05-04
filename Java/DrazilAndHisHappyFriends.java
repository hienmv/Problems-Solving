/** #number-theory #todo #refactor */
import java.util.Scanner;

class DrazilAndHisHappyFriends {
  public static int gdc(int a, int b) {
    int r;
    while (b != 0) {
      r = a % b;
      a = b;
      b = r;
    }
    return a;
  }

  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int[] boys = new int[n];
    int[] girls = new int[m];
    int b = sc.nextInt();
    int tmp;
    for (int i = 0; i < b; i++) {
      tmp = sc.nextInt();
      boys[tmp] = 1;
    }
    int g = sc.nextInt();
    for (int i = 0; i < g; i++) {
      tmp = sc.nextInt();
      girls[tmp] = 1;
    }

    // calculate
    int lcm = n * m / gdc(n, m);
    for (int i = 0; i < 2 * lcm; i++) {
      if (boys[i % n] == 1) {
        girls[i % m] = 1;
      } else if (girls[i % m] == 1) {
        boys[i % n] = 1;
      }
    }
    boolean ans = true;
    for (int i = 0; i < n; i++) {
      if (boys[i] == 0) {
        ans = false;
        break;
      }
    }
    if (ans) {
      for (int i = 0; i < m; i++) {
        if (girls[i] == 0) {
          ans = false;
          break;
        }
      }
    }
    System.out.println(ans ? "Yes" : "No");
  }
}
