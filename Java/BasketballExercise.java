/** https://codeforces.com/problemset/problem/1195/C
 * #dp #refactor
 *
 * 
 * better idea:
    - chon 0
    - chon 1
    - khong chon
    
    sum[0][j] : max sum chon dong 0 cot j
    sum[1][j] : max sum chon dong 1 cot j
    sum[2][j] : max sum khong chon cot j
    
    sum[0][j] = arr[0][j] + Math.max(sum[1][j-1], sum[2][j-1])
    sum[1][j] = arr[1][j] + Math.max(sum[0][j-1], sum[2][j-1])
    sum[2][j] = Math.max(sum[0][j-1], sum[1][j-1])
    
    
    for (int j = 1; j <= n; j++) {
        for (int i = 0; i < 2; i++) {
            sum[i][j] = Math.max(sum[1-i][j-1] + arr[i][j], sum[i][j-1]);
        }
    }
    result = Math.max(sum[0][n], sum[1][n]);
 */
import java.util.Scanner;
public class BasketballExercise {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] arr = new long[2][n+1];
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = sc.nextLong();
            }
        }
        
        long[][] sum = new long[2][n+1];
        sum[0][1] = arr[0][1];
        sum[1][1] = arr[1][1];
        for (int j = 2; j <= n; j++) {
            for (int i = 0; i < 2; i++) {
                sum[i][j] = Math.max(sum[1-i][j-1], Math.max(sum[i][j-2], sum[1-i][j-2]));
                sum[i][j] += arr[i][j];
            }
        }

        long result = Math.max(sum[0][n], sum[1][n]);
        if (n > 1) {
            result = Math.max(result, Math.max(sum[0][n-1], sum[1][n-1]));
        }
        System.out.println(result);
    }
}