/**
 * http://codeforces.com/problemset/problem/180/C #dynamic-programming for i in (1, n): calculate
 * cost if: 1 -> i : A, i+1 -> n: a
 */
import java.util.Scanner;

public class Letter {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    char[] s = sc.next().toCharArray();
    int n = s.length;
    int[] upperCount = new int[n + 1];
    for (int i = 1; i < upperCount.length; i++) {
      if (s[i - 1] < 'a') {
        upperCount[i] = upperCount[i - 1] + 1;
      } else {
        upperCount[i] = upperCount[i - 1];
      }
    }

    // calculate
    int cost = Integer.MAX_VALUE;
    // start 0 -> cover the case: make string to lowercase.
    for (int i = 0; i < upperCount.length; i++) {
      int costUpper = i - upperCount[i];
      int costLower = upperCount[n] - upperCount[i];
      cost = Math.min(cost, costUpper + costLower);
    }

    System.out.println(cost);
  }
}
