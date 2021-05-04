/** https://codeforces.com/problemset/problem/1234/C #implementation */
import java.util.Scanner;

public class Pipes {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int queries = sc.nextInt();
    for (int q = 0; q < queries; q++) {
      int n = sc.nextInt();
      char[][] arr = new char[2][n];
      // input
      for (int i = 0; i < 2; i++) {
        arr[i] = sc.next().toCharArray();
      }
      // process
      int row = 0;
      for (int col = 0; col < n; col++) {
        // no need to change row
        if (arr[row][col] < '3') {
          continue;
        }
        // need to change row
        if (arr[1 - row][col] < '3') {
          // can not forward
          row = 0;
          break;
        }
        row = 1 - row;
      }
      System.out.println((row != 1) ? "NO" : "YES");
    }
  }
}
