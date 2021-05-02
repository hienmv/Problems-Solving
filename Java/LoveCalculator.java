/**
 * #dynamic-programming #lcs
 * #refactor #simple-way #optimized #todo
 */
import java.util.Scanner;
class LoveCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for(int t=1; t <= testcase; t++) {
            String word1 = sc.next();
            String word2 = sc.next();
            int m = word1.length();
            int n = word2.length();
            int[][] L = new int[m+1][n+1];
            long[][] numWays = new long[m+1][n+1];
            lcs(word1, m, word2, n, L, numWays);
            int len = m + n - L[m][n];
            System.out.println("Case " + t + ": " + len + " " + numWays[m][n]);
        }
    }
    public static void lcs(String word1, int m, String word2, int n, int[][] L, long[][] numWays) {
        for(int i=0; i <= m; i++) {
            for(int j=0; j <= n; j++) {
                if (i==0 || j==0) {
                    L[i][j] = 0;
                    numWays[i][j] = 1;
                } 
                else if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    L[i][j] = L[i-1][j-1] + 1;
                    numWays[i][j] = numWays[i-1][j-1];
                }
                else {
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
                    if (L[i][j] == L[i-1][j]) {
                        L[i][j] = L[i-1][j];
                        numWays[i][j] += numWays[i-1][j];
                    }
                    if (L[i][j] == L[i][j-1]) {
                        L[i][j] = L[i][j-1];
                        numWays[i][j] += numWays[i][j-1];
                    }
                }
            }
        }
    }
/*    
    // simple-way
    findWays() {
        for(int i=0; i <= m; i++) {
            for(int j=0; j <=n; j++) {
                if(i==0 || j==0) {
                    permutation[i][j] = 1;
                }
                else {
                    // permutation[i][j] = permutation[i-1][j] + permutation[i][j-1];
                    if( L[i][j] == L[i-1][j]) {
                        permutation[i][j] += permutation[i-1][j];
                    }
                    if (L[i][j] == L[i][j-1]) {
                         permutation[i][j] += permutation[i][j-1];
                    }
                }
            }
        }
    }
    */
}