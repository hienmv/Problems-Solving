/**
 * #dp #lcs
 */

import java.util.Scanner;
class AdvancedFruits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String str1 = sc.next();
            String str2 = sc.next();
            // calculate
            int m = str1.length();
            int n = str2.length();
            int[][] L = new int[m+1][n+1];
            lcs(str1, m, str2, n, L);
            printResult(str1, m, str2, n, L);
            
        }
    }
    public static void printResult(String str1, int m, String str2, int n, int[][] L) {
        StringBuilder result = new StringBuilder();
        // edge case
        int len = L[m][n];
        if (len == 0) {
            result.append(str1);
            result.append(str2);
            System.out.println(result);
            return;
        }
        
        int i=m, j=n;
        Position[] common = new Position[len];
        while(i > 0 && j > 0) {
            if (str1.charAt(i-1) == str2.charAt(j-1)) {
                common[len - 1] = new Position(i-1,j-1); 
                i--; j--; len--;
            }
            else if (L[i-1][j] > L[i][j-1]) {
                i--;
            }
            else {
                j--;
            }
        }

        // append to result
        i = 0; j = 0;
        for (Position p : common) {
            result.append(str1.substring(i, p.pos1));
            result.append(str2.substring(j, p.pos2));
            result.append(str1.charAt(p.pos1));
            i = p.pos1 + 1;
            j = p.pos2 + 1;
        } 
        if (i < m) {
            result.append(str1.substring(i, m));
        }
        if (j < n) {
            result.append(str2.substring(j, n));
        }
        System.out.println(result);
    }
    public static void lcs(String str1, int m, String str2, int n, int[][] L) {
        for(int i=0; i <= m; i++) {
            for(int j=0; j <= n; j++) {
                if(i==0 || j==0) {
                    L[i][j] = 0;
                }
                else if(str1.charAt(i-1) == str2.charAt(j-1)) {
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
    int pos1;
    int pos2;
    Position(int pos1, int pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }
}