/** #string #hash-table */
import java.util.HashSet;
import java.util.Scanner;

class WattoAndMechanism {
  public static int base = 27;
  public static long MOD = 1_000_000_007L;
  public static int MAXN = 600_000 + 1;

  public static long hashCode(String s) {
    long hashValue = 0;
    int sz = s.length();
    int val;
    for (int i = 0; i < sz; i++) {
      val = s.charAt(i) - 'a' + 1;
      hashValue = (val + (base * hashValue) % MOD) % MOD;
    }
    return hashValue;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    long[] mul = new long[MAXN];
    mul[0] = 1;
    for (int i = 1; i < MAXN; i++) {
      mul[i] = (mul[i - 1] * base) % MOD;
    }

    String s;
    HashSet<Long> set = new HashSet<>();
    for (int i = 0; i < n; i++) {
      s = sc.next();
      set.add(hashCode(s));
    }
    for (int i = 0; i < m; i++) {
      s = sc.next();
      long hashCode = hashCode(s);
      boolean ok = false;
      int sz = s.length();
      for (int j = 0; j < sz; j++) {
        int val1 = s.charAt(j) - 'a' + 1;
        // a b c
        for (char ch = 'a'; ch <= 'c'; ch++) {
          if (ch == s.charAt(j)) {
            continue;
          }
          int val2 = ch - 'a' + 1;
          long hc = ((hashCode + (val2 - val1) * mul[sz - j - 1]) % MOD + MOD) % MOD;
          if (set.contains(hc)) {
            ok = true;
            break;
          }
        }
        if (ok) break;
      }
      if (ok) {
        System.out.println("YES");
      } else {
        System.out.println("NO");
      }
    }
  }
}
