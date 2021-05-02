/* https://codeforces.com/contest/835/problem/D
#string #hash-table #dynamic-programming

abacaba

1-palin: a, b, a, c, a, b, a
        aba, abacabc, bacab, aca, aba
2-palin: aba, aca, aba, abacabc

3-palin: abacabc (abacaba)
*/
import java.util.Scanner;
public class PalindromicCharacteristics {
    static int base = 27;
    static int MAXN = 5001;
    static long MOD = 1_000_000_007L;
    static long[] mul = new long[MAXN];
    static {
        // preprocess
        mul[0] = 1;
        for (int i=1; i < MAXN; i++) {
            mul[i] = (mul[i-1] * base) % MOD;
        }
    }
    public static long calculateHashCode(String s, long[] hashArr) {
        long hashValue = 0;
        int sz = s.length();
        int val;
        for (int i=0; i < sz; i++) {
            val = s.charAt(i) - 'a' + 1;
            hashValue = (val + (base * hashValue) % MOD) % MOD;
            hashArr[i+1] = hashValue;
        }
        return hashValue;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int sz = s.length() + 1;
        long[] hashArrLeft = new long[sz];
        long[] hashArrRight = new long[sz];
        // calculate hashcode
        calculateHashCode(s, hashArrLeft);
        calculateHashCode(new StringBuilder(s).reverse().toString(), hashArrRight);
        
        int[] result = new int[sz];
        int[][] palindrome = new int[sz][sz];
        int len, l, r;
        long hashLeft, hashRight;
        for (int i=1; i < sz; i++) {
            palindrome[i][i] = 1;
            result[palindrome[i][i]]++;
            for (int j=i+1; j < sz; j++) {
                len = (j - i + 1) / 2;
                // calculate hashCode left and right
                l = i; r = l + len - 1;
                /* hash(l, r) = (hash[r] - ((hash[l - 1] * pw[r - l + 1]) % MOD) + MOD) % MOD */
                hashLeft = (hashArrLeft[r] - (hashArrLeft[l - 1] * mul[r - l + 1]) % MOD + MOD) % MOD;
                l = sz - j; r = l + len - 1;
                hashRight = (hashArrRight[r] - (hashArrRight[l - 1] * mul[r - l + 1]) % MOD + MOD) % MOD;
                
                /* refactor -> not use hash table.
                i ... j
                i, i+1 ... j-1, j
                palindrome[i+1][j-1] > 0
                if palindrome[i+1][j-1] > 0 and s[i] == s[j]
                => i ... j is a palin
                for j : 1 => sz - 1:
                    for i: j ... 1
                        if ((s[i] == s[j]) && (i + 1 >= j - 1 || palindrome[i+1][j-1] > 0)) {
                            // palin
                        }
                        else {
                            palindrome[i][j] = 0
                        }
                */

                // check palindrome
                if (hashLeft == hashRight) {
                    palindrome[i][j] = 1;
                    if (palindrome[i][i + len - 1] > 0) {
                        palindrome[j - len + 1][j] = palindrome[i][i + len - 1];
                        palindrome[i][j] += palindrome[j - len + 1][j];
                    }
                    result[palindrome[i][j]]++;
                }
            }
        }
        for (int i= result.length - 2; i >= 1; i--) {
            result[i] += result[i+1];
        }
        for (int i=1; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}