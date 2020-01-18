/*
https://codeforces.com/problemset/problem/1105/C

divisible by 3:
    numWays(n, 0):
        (x % 3)  numWays(n - 1, (3 - (x % 3)) % 3)

    O(n * (r - l + 1))
    
    
  x -> [l, r] : x % 3 -> 0, 1, 2
  
  calculate by group
  [l, r ] = > 0 .... m
              1 .... q
              2 .... k
*/
import java.util.Scanner;
public class AyoubAndLostArray {
    private static int MOD = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        // count number of number that modules 3 equals 0, 1, 2.
        int[] cnt = new int[3];
        for (int i = 0; i < 3; i++) {
            cnt[(l + i) % 3] = (r - l - i) >= 0 ? ((r - l - i) / 3 + 1) : 0; 
        }

        long[][] L = new long[n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    L[i][j] = cnt[j];
                    continue;
                }
                if (j != 0) {
                    L[i][j] = (L[i][j] + (cnt[j] * L[i - 1][0]) % MOD) % MOD;
                    L[i][j] = (L[i][j] + (cnt[0] * L[i - 1][j]) % MOD) % MOD;
                    L[i][j] = (L[i][j] + (cnt[3 - j] * L[i - 1][3 - j]) % MOD) % MOD;
                }
                else {
                    L[i][j] = (L[i][j] + (cnt[j] * L[i - 1][0]) % MOD) % MOD;
                    L[i][j] = (L[i][j] + (cnt[2] * L[i - 1][1]) % MOD) % MOD;
                    L[i][j] = (L[i][j] + (cnt[1] * L[i - 1][2]) % MOD) % MOD;
                }
            }
        }

        System.out.println(L[n-1][0]);
    }
}
