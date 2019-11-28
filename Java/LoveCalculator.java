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
            int len = m + n - L[m][n];
            int uniques = getShortestStringNum(word1, m, word2, n, L);
            System.out.println("Case " + t + ": " + len + " " + uniques);
        }
    }
    public static int getShortestStringNum(String word1, int m, String word2, int n, int[][] L) {
        /* calcuate numbers of order when merging two strings 
        * that their lengths are a and b respectively.
        */
        int[][] permutation = new int[m+1][n+1];
        for(int k=0; k <= m; k++) {
            for(int l=0; l <=n; l++) {
                if(k==0 || l==0) {
                    permutation[k][l] = 1;
                }
                else {
                    permutation[k][l] = permutation[k-1][l] + permutation[k][l-1];
                }
                //System.out.print(permutation[k][l] + ", ");
            }
            //System.out.println();
        }
        //System.out.println("AA");
        
        int len = L[m][n];
        // edge case
        if (len == 0) {
            return permutation[m][n];
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
            //System.out.print(a + "::" + b);
            result *= permutation[a][b];
            i = p.pos1 + 1;
            j = p.pos2 + 1;
        } 
        if (i < m || j < n) {
            a = m - i;
            b = n - j;
            //System.out.print(a + "**" + b);
            result *= permutation[a][b];
        }
        return result;
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