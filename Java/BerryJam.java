/**
 * https://codeforces.com/problemset/problem/1278/C #implementation #binary-search
 *
 * <p>#note: the difference /* arr[2n] diff = 0 loop 0 -> 2n-1 read arr[i] diff += arr[i]
 *
 * <p>loop n -> 2n-1 diffRight += arr[i] if !hashmap.containsKey(diffRight) hashmap[diffRight] = i -
 * n + 1
 *
 * <p>loop n-1 -> 0 diffLeft = ... if hasmap.contains(diff - diffLeft) -> update result
 */
import java.util.HashMap;
import java.util.Scanner;

public class BerryJam {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testcases = sc.nextInt();
    for (int t = 0; t < testcases; t++) {
      int n = sc.nextInt();
      int[] arr = new int[2 * n + 1];
      int v, totalDiff = 0;
      for (int i = 1; i < 2 * n + 1; i++) {
        v = sc.nextInt();
        if (v == 1) {
          arr[i] = 1;
        } else {
          arr[i] = -1;
        }
        totalDiff += arr[i];
      }
      // corner case : no move anywhere
      if (totalDiff == 0) {
        System.out.println(0);
        continue;
      }

      int diffRight = 0;
      HashMap<Integer, Integer> mapRight = new HashMap<>();
      mapRight.put(0, 0); // not move to forward
      for (int i = n + 1; i < 2 * n + 1; i++) {
        diffRight += arr[i];
        if (!mapRight.containsKey(diffRight)) {
          mapRight.put(diffRight, i - n);
        }
      }

      // backward case
      int result = 2 * n;
      // when not move to backward
      if (mapRight.containsKey(totalDiff)) {
        result = mapRight.get(totalDiff);
      }
      int diffLeft = 0;
      for (int i = n; i > 0; i--) {
        diffLeft += arr[i];
        if (mapRight.containsKey(totalDiff - diffLeft)) {
          result = Math.min(n - i + 1 + mapRight.get(totalDiff - diffLeft), result);
        }
      }
      System.out.println(result);
    }
  }
}
