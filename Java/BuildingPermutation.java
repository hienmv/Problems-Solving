/** https://codeforces.com/problemset/problem/285/C #greedy #sorting */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class BuildingPermutation {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    boolean[] arr = new boolean[n + 1];
    ArrayList<Integer> needChangeList = new ArrayList<>();
    int tmp = 0;
    for (int i = 0; i < n; i++) {
      tmp = sc.nextInt();
      if (tmp <= n && tmp > 0 && !arr[tmp]) {
        arr[tmp] = true;
      } else {
        needChangeList.add(tmp);
      }
    }
    Collections.sort(needChangeList);

    long cost = 0;
    int idx = 0;
    for (int i = 1; i < arr.length && idx < needChangeList.size(); i++) {
      if (arr[i]) {
        continue;
      }
      cost += (long) Math.abs(i - needChangeList.get(idx));
      idx++;
    }
    System.out.println(cost);
  }
}

/**
 * other idea: just sort, min-min, max-max
 *
 * <p>a -> p
 *
 * <p>a[i] -> p[i]
 *
 * <p>a[i] a[i] < a[j] a[j]
 *
 * <p>p[i] p[j]
 *
 * <p>p[i] = a[j] < p[j]
 *
 * <p>a[i] ... a[j] a[j] ... p[j]
 *
 * <p>a[i] ... p[i]
 *
 * <p>a[j] ... p[j]
 *
 * <p>sort(a) abs(a[i] - (i+1))
 *
 * <p>a[i] = 0
 */
