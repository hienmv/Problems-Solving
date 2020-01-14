/**
 * #lcs #dp
 * K = 3
 * 1 2 3 4 5 6
 * 4 5 6 1 2 3
 */
import java.util.Scanner;
class MancunianKOrderedLCS {
    public static void main(String[] args) {
        Scanner sc = new  Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[] arrA = new int[n];
        for (int i = 0; i < n; i++) {
            arrA[i] = sc.nextInt();
        }
        int[] arrB = new int[m];
        for (int i = 0; i < m; i++) {
            arrB[i] = sc.nextInt();
        }
        int result = lcs(arrA, arrB, k);
        System.out.println(result);

    }
    private static int lcs(int[] arrA, int[] arrB, int k) {
        int n = arrA.length;
        int m = arrB.length;
        int[][][] L = new int[n+1][m+1][k+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int q = 0; q <= k; q++) {
                    if (i == 0 || j == 0) {
                        L[i][j][q] = 0;
                    }
                    else if (arrA[i - 1] == arrB[j - 1]) {
                        L[i][j][q] = L[i - 1][j - 1][q] + 1;
                    }
                    else {
                        L[i][j][q] = Math.max(L[i - 1][j][q], L[i][j - 1][q]);
                        if (q > 0) {
                            L[i][j][q] = Math.max(L[i][j][q], L[i - 1][j - 1][q - 1] + 1);
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i <= k; i++) {
            result = Math.max(result, L[n][m][i]);
        }
        return result;
    }
}