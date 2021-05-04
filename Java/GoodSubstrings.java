/** #hash-table */
import java.util.HashSet;
import java.util.Scanner;

public class GoodSubstrings {
  public static int BASE = 31;
  public static long MOD = 9_223_372_036_854_775_807L;

  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    String verifyString = sc.next();
    int maxBadCharNum = sc.nextInt();
    int sz = s.length();
    // calculate
    long hashCode;
    int badCharNum, c;
    HashSet<Long> hashSet = new HashSet<>();
    for (int left = 0; left < sz; left++) {
      hashCode = 0;
      badCharNum = 0;
      for (int right = left; right < sz; right++) {
        c = s.charAt(right) - 'a';
        hashCode = (c + 1 + (BASE * hashCode) % MOD) % MOD;
        badCharNum += verifyString.charAt(c) == '0' ? 1 : 0;
        if (badCharNum <= maxBadCharNum) {
          hashSet.add(hashCode);
        } else {
          break;
        }
      }
    }
    long goodSubStrNum = hashSet.size();
    System.out.println(goodSubStrNum);
  }
}
