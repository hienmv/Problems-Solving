/**
 * #dynamic-programming #lcs
 */
import java.util.Scanner;
import java.util.ArrayList;

class CrossCountry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = Integer.parseInt(sc.nextLine());
        for(int t=0; t < testcase; t++) {
            String girlLine = sc.nextLine();
            String[] girlArr = girlLine.split(" ");
            int result = 0;
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equals("0")) {
                    System.out.println(result);
                    break;
                }
                else {
                    String[] boyArr = line.split(" ");
                    result = Math.max(result, lcs(girlArr, boyArr));
                }
            }
        }
    }
    public static int lcs(String[] arrA, String[] arrB) {
        int n = arrA.length;
        int m = arrB.length;
        int[][] L = new int[n][m];
        for(int i=0; i < n; i++) {
            for(int j=0; j < m; j++) {
                if (i == 0 || j == 0) {
                    L[i][j] = 0;
                }
                else if (arrA[i-1].equals(arrB[j-1])) {
                    L[i][j] = 1 + L[i-1][j-1];
                }
                else {
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
                }
            }
        }
        return L[n-1][m-1];
    }
}