/**
 * https://codeforces.com/contest/1108/problem/D #greedy // *****BGB**** #todo check 1 vong for.
 * current i, check with i-1 => have to change ? change at i.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DiverseGarland {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    char[] color = sc.next().toCharArray();
    int minimunChange = 0;
    int l = 0, r = 0;

    char[] colorArr = {'R', 'G', 'B'};
    HashMap<Character, Integer> map = new HashMap<>();
    map.put('R', 0);
    map.put('G', 1);
    map.put('B', 2);
    ArrayList<Pair> list = new ArrayList<>();

    boolean countFlg = false;
    for (int i = 1; i < n; i++) {
      if (color[i] == color[i - 1]) {
        if (countFlg) {
          r++;
        } else {
          countFlg = true;
          l = i - 1;
          r = i;
        }
      } else {
        if (countFlg) {
          list.add(new Pair(l, r));
        }
        countFlg = false;
      }
    }
    // check last element
    if (countFlg) {
      list.add(new Pair(l, r));
    }

    // calculate
    for (Pair p : list) {
      l = p.l;
      r = p.r;
      minimunChange += (r - l + 1) / 2;

      // range size = 2
      if (r == l + 1) {
        if (r == n - 1) {
          color[r] = colorArr[(map.get(color[r]) + 1) % 3];
        } else {
          color[r] = colorArr[3 - (map.get(color[l]) + map.get(color[r + 1]))];
        }
      } else {
        // ignore boundaries
        r--;
        l++;

        while (r > l + 1) {
          color[r] = colorArr[(map.get(color[r]) + 1) % 3];
          color[l] = colorArr[(map.get(color[l]) + 1) % 3];
          r -= 2;
          l += 2;
        }
        // range size is even
        if (Math.abs(l - r) == 1) {
          l = Math.min(l, r);
          r = l + 1;

          color[l] = colorArr[(map.get(color[l]) + 1) % 3];
          if (r == n - 1) {
            color[r] = colorArr[(map.get(color[r]) + 1) % 3];
          } else {
            color[r] = colorArr[3 - (map.get(color[l]) + map.get(color[r + 1]))];
          }
        }
        // range size is odd
        if (r == l) {
          color[l] = colorArr[(map.get(color[l]) + 1) % 3];
        }
      }
    }
    // result
    System.out.println(minimunChange);
    for (int i = 0; i < n; i++) {
      System.out.print(color[i]);
    }
  }
}

class Pair {
  int l, r;

  Pair(int l, int r) {
    this.l = l;
    this.r = r;
  }
}
