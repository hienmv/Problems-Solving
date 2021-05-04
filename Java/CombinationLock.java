/*  https://codeforces.com/problemset/problem/540/A
tag: #implementation
    compare a[i], b[i]
    if (a[i] - b[i]) > 5; result = a[i] - b[i] - 10;
*/
import java.util.Scanner;

public class CombinationLock {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String str1 = sc.next();
    String str2 = sc.next();
    char[] arr1 = str1.toCharArray();
    char[] arr2 = str2.toCharArray();
    int result = 0;
    int temp = 0;
    for (int i = 0; i < n; i++) {
      temp = Math.abs(arr1[i] - arr2[i]);
      if (temp > 5) {
        temp = 10 - temp;
      }
      result += temp;
    }
    System.out.println(result);
  }
}
