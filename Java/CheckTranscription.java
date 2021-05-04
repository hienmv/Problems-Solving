/**
 * https://codeforces.com/problemset/problem/1056/E #string #hash-table
 *
 * <p>001 -> number of '0', '1' is fixed. kokokokolin -> number of characters is fixed. -> choose
 * prefix for '0' (fixed) ... substring for '1' -> number of choosen ways depends on a somehow
 * formular.
 *
 * <p>0 0 1 kokokokotlin
 *
 * <p>s -> c0 c1 |t| = c0 * |r0| + c1 * |r1| len_t = c0 * l0 + c1 * l1
 *
 * <p>for l0 : 1 -> len_t if ((len_t - c0 * r0) % c1 != 0) continue; l1 = (len_t - c0 * r0) / c1
 *
 * <p>=> select l0 -> select a proper l1. => optimize the comprasion of two substring -> hashing.
 */
import java.util.Scanner;

public class CheckTranscription {
  static int base = 27;
  static int MAXN = 1000001;
  static long MOD = 1_000_000_007L;
  static long[] mul = new long[MAXN];

  static {
    // preprocess
    mul[0] = 1;
    for (int i = 1; i < MAXN; i++) {
      mul[i] = (mul[i - 1] * base) % MOD;
    }
  }

  public static long calculateHashCode(String s, long[] hashCodeArr) {
    long hashValue = 0;
    int sz = s.length();
    int val;
    for (int i = 0; i < sz; i++) {
      val = s.charAt(i) - 'a' + 1;
      hashValue = (val + (base * hashValue) % MOD) % MOD;
      hashCodeArr[i + 1] = hashValue;
    }
    return hashValue;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    String t = sc.next();
    // calculate
    long[] hashCodeArr = new long[t.length() + 1];
    calculateHashCode(t, hashCodeArr);
    System.out.println(calculate(s, t, hashCodeArr));
  }

  public static int calculate(String s, String t, long[] hashCodeArr) {
    // count number of '0', '1'
    int num0 = 0, num1 = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '0') {
        num0++;
      } else {
        num1++;
      }
    }
    // calculate
    int result = 0;
    int lenT = t.length();
    for (int l0 = 1; l0 < lenT; l0++) {
      if ((lenT - l0 * num0) % num1 != 0) continue;
      int l1 = (lenT - l0 * num0) / num1;
      long hashCode0 = 0, hashCode1 = 0, hash;
      int pre = 0;
      boolean skip = false;
      int maxLen = s.length();
      for (int i = 0; i < maxLen; i++) {
        if (s.charAt(i) == '0') {
          hash = getHashCodeSubString(t, pre, pre + l0, hashCodeArr);
          if (hashCode0 == 0) {
            hashCode0 = hash;
          }
          if (hash < 0 || hashCode0 != hash || hashCode0 == hashCode1) {
            skip = true;
            break;
          }
          pre += l0;
        } else {
          hash = getHashCodeSubString(t, pre, pre + l1, hashCodeArr);
          if (hashCode1 == 0) {
            hashCode1 = hash;
          }
          if (hash < 0 || hashCode1 != hash || hashCode0 == hashCode1) {
            skip = true;
            break;
          }
          pre += l1;
        }
      }
      if (skip) continue;
      result++;
    }
    return result;
  }

  public static long getHashCodeSubString(String source, int left, int right, long[] hashCodeArr) {
    if (right >= source.length()) right = source.length();
    if (left >= right) return -1;
    int l = left + 1, r = right;
    /* hash(l, r) = (hash[r] - ((hash[l - 1] * pw[r - l + 1]) % MOD) + MOD) % MOD */
    return (hashCodeArr[r] - ((hashCodeArr[l - 1] * mul[r - l + 1]) % MOD) + MOD) % MOD;
  }
}
