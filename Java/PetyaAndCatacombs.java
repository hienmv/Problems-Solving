/** https://codeforces.com/problemset/problem/886/C #greedy #hash-table */
import java.util.HashSet;
import java.util.Scanner;

class PetyaAndCatacombs {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    HashSet<Integer> minuteSet = new HashSet<>();
    minuteSet.add(0);
    int minute;
    for (int i = 1; i < n + 1; i++) {
      minute = sc.nextInt();
      if (minuteSet.contains(minute)) {
        minuteSet.remove(minute);
      }
      minuteSet.add(i);
    }
    System.out.println(minuteSet.size());
  }
}
