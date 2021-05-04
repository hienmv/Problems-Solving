/* idea
  s 1110101001
    -> s1, s0
  t 10101
  arr
    1 for

    -> t1, t0

  10101..

         |
  1 0 1 0 1
      x

  1 1 1 0 1
    x
    1 1 1 0 1
c0 = 1
c1 = 1

*/
// 111011101
// => overlap: 11101 -> suffix = 1101 -> need0, need1
// t: 111011101
// t2: 1110111011101
// t3: t2 + 1101
// ti = ti-1 + 1101
//
//   |T| + |T| - l => l max
//  *
// 1110111011101
//  y   x
/*
    cnt0, cnt1 of s
    edge case
    res = t, need0, need1
    cnt0 -= cntT0, cnt1 -= cntT1
    while cnt0 >= need0 and cnt1 >= need1


*/
/** #string #hash-table */
import java.util.Scanner;

class CampSchedule {
  public static int base = 3;
  public static long MOD = 1_000_000_007L;
  public static int MAXN = 500001;
  public static long[] hashArr = new long[MAXN];

  // calculata hashcode of a string
  public static long hashCode(String s) {
    long hashValue = 0;
    int sz = s.length();
    int val;
    for (int i = 0; i < sz; i++) {
      val = s.charAt(i) - '0' + 1;
      hashValue = (val + (base * hashValue) % MOD) % MOD;
      hashArr[i + 1] = hashValue;
    }
    return hashValue;
  }

  public static void main(String[] args) {
    // pre-calculate for calculation of hashcode
    long[] mul = new long[MAXN];
    mul[0] = 1;
    for (int i = 1; i < MAXN; i++) {
      mul[i] = (mul[i - 1] * base) % MOD;
    }

    // input
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    String t = sc.next();
    // count number of 0, 1
    Number source = new Number(0, 0);
    for (char c : s.toCharArray()) {
      if (c == '0') {
        source.num0 += 1;
      } else {
        source.num1 += 1;
      }
    }
    // count number of 0, 1 of pattern
    int sz = t.length();
    int[] arr0 = new int[sz];
    int[] arr1 = new int[sz];
    char[] arr = t.toCharArray();
    Number dest = new Number(0, 0);
    for (int i = 0; i < sz; i++) {
      if (arr[i] == '0') {
        dest.num0 += 1;
        arr0[i] = (i == 0) ? 1 : (arr0[i - 1] + 1);
        arr1[i] = (i == 0) ? 0 : arr1[i - 1];
      } else {
        dest.num1 += 1;
        arr1[i] = (i == 0) ? 1 : (arr1[i - 1] + 1);
        arr0[i] = (i == 0) ? 0 : arr0[i - 1];
      }
    }

    // edge case
    if (source.num1 < dest.num1 || source.num0 < dest.num0) {
      System.out.println(s);
      return;
    }

    // find max suffix
    /* res = t + suffix
    int maxLength = 0;
    for (int i = 1; i < t.length((); i++)) {
        if (hashArr[n - i] == ((hashArr[n] - (hashArr[i] * mul[n - i]) % MOD) + MOD) % MOD) {
            maxLength = n - i;
            break;
        }
    }

    Number need = new Number(0, 0);
    for (int i = maxLength; i < t.length; i++) {
    }
    StringBuilder res = new StringBuilder(t);
    t = t.substring(maxLength);
    */
    long hashCode = hashCode(t);
    source.num0 -= dest.num0;
    source.num1 -= dest.num1;
    Number need = new Number(0, 0);
    String suffix = "";
    for (int i = 1; i < t.length(); i++) {
      suffix = maxSuffix(i, mul, arr0, arr1, t, hashCode, source, need);
      if (!suffix.isEmpty()) {
        break;
      }
    }
    // calculate result
    StringBuilder res = new StringBuilder(t);
    if (!suffix.isEmpty()) {
      while (source.num0 >= need.num0
          && source.num1 >= need.num1
          && need.num0 > 0
          && need.num1 > 0) {
        res.append(suffix);
        source.num0 -= need.num0;
        source.num1 -= need.num1;
      }
    }
    while (source.num0 >= dest.num0 && dest.num0 > 0 && source.num1 >= dest.num1 && dest.num1 > 0) {
      res.append(t);
      source.num0 -= dest.num0;
      source.num1 -= dest.num1;
    }
    while (source.num0 > 0) {
      res.append(0);
      source.num0 -= 1;
    }
    while (source.num1 > 0) {
      res.append(1);
      source.num1 -= 1;
    }
    System.out.println(res);
  }

  // find max common suffix from a position of pattern and pattern
  public static String maxSuffix(
      int idx,
      long[] mul,
      int[] arr0,
      int[] arr1,
      String t,
      long hashCode,
      Number source,
      Number need) {
    if (t.charAt(idx) != t.charAt(0)) {
      return "";
    }
    int n = t.length();
    int len = n - idx;
    int need0 = arr0[idx - 1];
    int need1 = arr1[idx - 1];
    if ((hashArr[len] == ((hashArr[n] - (hashArr[n - len] * mul[len]) % MOD) + MOD) % MOD)
        && source.num0 >= need0
        && source.num1 >= need1) {
      need.num0 = need0;
      need.num1 = need1;
      return t.substring(len, n);
    }
    return "";
  }
}

class Number {
  int num0, num1;

  Number(int num0, int num1) {
    this.num0 = num0;
    this.num1 = num1;
  }
}
