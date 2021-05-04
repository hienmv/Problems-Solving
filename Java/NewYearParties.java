/*
https://codeforces.com/problemset/problem/1283/E
=> min
=> max
    => independence => seperate two .
        => two sub-problems
#greedy
*/
import java.util.Scanner;

public class NewYearParties {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    int maxVal = 0;
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
      if (arr[i] > maxVal) {
        maxVal = arr[i];
      }
    }
    // count array
    int[] cntArr = new int[maxVal + 1];
    for (int v : arr) {
      cntArr[v]++;
    }

    // find min
    int min = 0;
    int i = 1;
    while (i < maxVal + 1) {
      if (cntArr[i] > 0) {
        /*
            i : cnt[i] > 0
                || cnt[i+1] > 0
                || cnt[i+2] > 0
                        => select pos = i+1 for both i, i+1, i+2.
        */
        i += 3;
        min++;
      } else {
        i++;
      }
    }

    // find max
    int max = 0;
    int prev = 0; // number of free's that can move back or forward
    for (i = 1; i < maxVal + 1; i++) {
      if (prev > 0) { // use prev only
        max++;
        prev = cntArr[i];
        cntArr[i] = 1;
      } else { // need to use at least one at i.
        if (cntArr[i] > 0 && cntArr[i - 1] == 0) { // move left
          cntArr[i - 1]++;
          cntArr[i]--;
          max++; // count for i - 1
        }
        if (cntArr[i] > 0) {
          max++; // count for i
          prev = cntArr[i] - 1;
        }
      }
    }
    // right bound
    if (prev > 0) max++;

    System.out.println(min + " " + max);
  }
}
