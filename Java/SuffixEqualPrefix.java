/**
 * #string #hash-table
 */
/**
    /*
    hash[i] = hashFunc(0 ... i)
    
    hash(l, r)
    
    hash[r] =     hash(0, r) =   a[0] * k^(n-1) + a[1] * k^(n-2) + ... +                           a[r] * k^(x)
    hash[l - 1] = hash(0, l-1) = a[0] * k^(n-1) + a[1] * k*(n-2) + ... + a[l-1] * k^(y)
    
    12345
    123 * k^p
    210
    
    hash[0] = 0
    for i: 1 ... n:
        hash[i] = (hash[i - 1] * BASE + a[i] - 'a') % MOD
    
    pw[0] = 1
    for i: 1 ... n:
        pw[i] = (pw[i - 1] * BASE) % MOD
    
    hash(l, r) = (hash[r] - (hash[l - 1] * pw[r - l + 1]) % MOD + MOD) % MOD
    
    pre[0..i] = suf[j..n-1]
    hash(1, i+1) ... hash(j + 1, n)
    
    abcabc
        abc
 */
import java.util.Scanner;

class SuffixEqualPrefix {
    public static int base = 31;
    public static int MOD = 1_000_000_007;
    public static long hashCode(String str, long[] hashVal) {
        long hashCode = 0;
        int sz = str.length();
        int val;
        for (int i=0; i < sz; i++) {
            val = str.charAt(i) - 'a' + 1;
            hashCode = (val + ((base * hashCode) % MOD)) % MOD;
            hashVal[i+1] = hashCode;
        }
        return hashCode;
    }
    public static void main(String[] args) {
        // pre-calculate
        int max = 1_000_001;
        long[] mul = new long[max];
        mul[0] = 1;
        for (int i=1; i < max; i++) {
            mul[i] = (mul[i-1] * base) % MOD;
        }

        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        String line;
        for (int t=1; t <= testcase; t++) {
            line = sc.next();
            int sz = line.length();
            long[] hashVal = new long[sz+1];
            hashCode(line, hashVal);

            int suffixEqualPrefixCount = 0;
            for (int i=1; i < sz; i++) {
                if(hashVal[i] == ((hashVal[sz] - (hashVal[sz - i] * mul[i]) % MOD) + MOD) % MOD) {
                    suffixEqualPrefixCount++;
                }
            } 
            System.out.println("Case " + t + ": " + suffixEqualPrefixCount);
        }
    }
}