/** https://codeforces.com/problemset/problem/486/B
 * #greedy
 *  create 1 temporary result based on input -> verify that result based on re-creating input matrix.
 */
import java.util.Scanner;
import java.util.Arrays;
public class ORMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] arr = new int[m+1][n+1];
        // create a temp result 
        int[][] result = new int[m+1][n+1];
        for(int i=1; i <= m; i++) Arrays.fill(result[i], 1);

        for (int i=1; i <= m; i++) {
            for (int j=1; j <= n; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == 0) {
                    for (int k=1; k <= m; k++) {
                        result[k][j] = 0;
                    }
                    for (int k=1; k <= n; k++) {
                        result[i][k] = 0;
                    }
                }
            }
        }
        
        // check re-create arr from result;
        for (int i=1; i <= m; i++) {
            for (int j=1; j <= n; j++) {
                int tmp = 0;
                for (int k = 1; k <= m; k++) {
                    tmp |= result[k][j];
                }
                for (int k= 1; k <= n; k++) {
                    tmp |= result[i][k];
                }
                if (arr[i][j] != tmp) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
        for (int i=1; i <= m; i++) {
            for (int j=1; j <= n; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}