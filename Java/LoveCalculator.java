/**
 * #dp #lcs
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
            int[][] L = new int [m+1][n+1];
            lcs(word1, m, word2, n, L);
            int uniques = getShortestStringNum(word1, m, word2, n, L);
            System.out.println("Case " + t + ": " + L[m][n] + " " + uniques);
        }
    }
    public static int getShortestStringNum(String word1, int m, String word2, int n, int[][] L) {
        int len = L[m][n];
        // edge case
        if (len == 0) {
            return calculateOrderNums(m, n);
        }

        int i=m, j=n;
        Position[] common = new Position[len];
        while(i > 0 && j > 0) {
            if (word1.charAt(i-1) == word2.charAt(j-1)) {
                common[len - 1] = new Position(i - 1, j - 1);
                i--; j--; len--;
            }
            else if (L[i-1][j] > L[i][j-1]) {
                i--;
            }
            else {
                j--;
            }
        }

        // calculate
        int result = 1;
        i = 0; j = 0;
        int a, b;
        for (Position p : common) {
            a = p.pos1 - i;
            b = p.pos2 - j;
            result *= calculateOrderNums(a, b);
            i = p.pos1;
            j = p.pos2;
        } 
        if (i < m || j < n) {
            a = m - 1 - i;
            b = n - 1 - j;
            result *= calculateOrderNums(a, b);
        }
        return result;
    }
    // calcuate numbers of order when merging two string that their length are a and b respectively.
    public static int calculateOrderNums(int a, int b) {
        // edge case
        if (a < 1 || b < 1) return 1;

        return 1;
    }
    public static void lcs(String word1, int m, String word2, int n, int[][] L) {
        for(int i=0; i <= m; i++) {
            for(int j=0; j <= n; j++) {
                if (i==0 || j==0) {
                    L[i][j] = 0;
                } 
                else if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    L[i][j] = L[i-1][j-1] + 1;
                }
                else {
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
                }
            }
        }
    }
}
class Position {
    int pos1, pos2;
    Position(int pos1, int pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }
}