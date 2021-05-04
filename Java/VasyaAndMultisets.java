/**
 * https://codeforces.com/problemset/problem/1051/C #implementation #todo
 *
 * <p>cnt[x] cntNice = 0
 *
 * <p>for x in a: if (!cnt.containsKey(x)): cntNice++; cnt.set(x, 1); else: temp = cnt.get(x)
 * cnt.set(x, temp + 1) if temp==1: cntNice--; needNewNice = false if cntNice % 2 == 1: needNewNice
 * = true
 *
 * <p>flag = false for key in cnt: if cnt.get(key) > 2: flag = true break if !flag: return FALSE
 * subset = 'A' for x in a: if cnt.get(x) == 1: print subset subset = 'A' + 'B' - subset else: if
 * needNewNice && cnt.get(x) > 2: print 'B' needNewNice = false else: print 'A'
 */
import java.util.HashMap;
import java.util.Scanner;

public class VasyaAndMultisets {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    HashMap<Integer, Integer> map = new HashMap<>();
    int niceNumbers = 0;
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
      int k = arr[i];
      if (map.containsKey(k)) {
        int v = map.get(k);
        map.replace(k, v + 1);
        if (v == 1) {
          niceNumbers -= 1;
        }
      } else {
        niceNumbers += 1;
        map.put(k, 1);
      }
    }

    // check need to create newNiceNumber
    boolean needNiceNumber = false;
    if (niceNumbers % 2 == 1) {
      needNiceNumber = true;
      // check whether we can make nice number or not
      boolean canMakeNiceNumber = false;
      for (int k : map.keySet()) {
        if (map.get(k) > 2) {
          canMakeNiceNumber = true;
          break;
        }
      }
      if (!canMakeNiceNumber) {
        System.out.println("NO");
        return;
      }
    }

    // print result
    System.out.println("YES");
    char subSet = 'A';
    for (int k : arr) {
      if (map.get(k) == 1) {
        System.out.print(subSet);
        // change subset
        subSet = (char) ('A' + 'B' - subSet);
      } else {
        if (needNiceNumber && map.get(k) > 2) {
          // ood niceNumbers => always in subset B
          System.out.print('B');
          needNiceNumber = false;
        } else {
          System.out.print('A');
        }
      }
    }
  }
}
