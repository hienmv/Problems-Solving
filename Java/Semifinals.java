/*  https://codeforces.com/contest/378/problem/B
#sorting #implementation

    not care about whick k should be choosen in each semifinal.
    first k element in each semifinals will be choose.
    in each semifinals, in each element in k-th -> n will be choose
    if it less than (n - k - 1) -th in the other simifinal.
*/

import java.util.Scanner;

public class Semifinals {

  static void updateResult(int[] ip1, int[] ip2, char[] result1, char[] result2) {
    int n = result1.length;
    for (int i = n / 2; i < n; i++) {
      if (ip1[i] < ip2[n - i - 1]) {
        result1[i] = '1';
      }
      if (ip2[i] < ip1[n - i - 1]) {
        result2[i] = '1';
      }
    }
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    // input
    int[] ip1 = new int[n];
    int[] ip2 = new int[n];
    for (int i = 0; i < n; i++) {
      ip1[i] = scanner.nextInt();
      ip2[i] = scanner.nextInt();
    }
    scanner.close();

    // result
    char[] result1 = new char[n];
    char[] result2 = new char[n];
    int mid = n / 2;
    for (int i = 0; i < n; i++) {
      result1[i] = i < mid ? '1' : '0';
      result2[i] = i < mid ? '1' : '0';
    }
    // update result
    updateResult(ip1, ip2, result1, result2);
    // show result
    System.out.println(new String(result1));
    System.out.println(new String(result2));
  }
}
