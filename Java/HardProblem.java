/*
* http://codeforces.com/problemset/problem/706/C
* #dynamic-programming #string #todo
i phu thuoc i - 1
i + 1 phu thuoc i
#dynamic-programming => nhin bai toan 1 chieu.
    => co dinh 1 chieu.
    => dp (xay dung dc bai toan 1 chieu. 
        i - 1 k phu thuoc vao i, i khong phu thuoc vao i + 1)

/* i -> i + 1
 - 0
  + arr[i-1]          <= arr[i]
    dp[i][0] = dp[i-1][0]
  + reverse(arr[i-1]) <= arr[i] 
    dp[i][0] = dp[i-1][1]
 - 1
  + arr[i-1]          <= reverse(arr[i])
    dp[i][1] = dp[i-1][0] + cost[i]
  + reverse(arr[i-1]) <= reverse(arr[i])
    dp[i][1] = dp[i-1][1] + cost[i]
*/
import java.util.Scanner;
public class HardProblem {
    private static long invalidMax = 1000000000000000L;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] costs = new long[n];
        for (int i = 0; i < n; i++) {
            costs[i] = sc.nextLong();
        }
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }
        // 0 - current string
        // 1 - reversed string
        long[][] L = new long[n][2]; // refactor L[2][2]
        L[0][1] = costs[0]; // initialized values
        String prev = arr[0];
        String prevRev = (new StringBuffer(prev)).reverse().toString();
        for (int i = 1; i < n; i++) {
            String rev = (new StringBuffer(arr[i])).reverse().toString();

            L[i][0] = L[i][1] = invalidMax;

            if (prev.compareTo(arr[i]) <= 0) {
                L[i][0] = Math.min(L[i][0], L[i - 1][0]);
            }
            if (prevRev.compareTo(arr[i]) <= 0) {
                L[i][0] = Math.min(L[i][0], L[i - 1][1]);
            }
            
            if (prev.compareTo(rev) <= 0) {
                L[i][1] = Math.min(L[i][1], L[i - 1][0] + costs[i]);
            }
            if (prevRev.compareTo(rev) <= 0) {
                L[i][1] = Math.min(L[i][1], L[i - 1][1] + costs[i]);
            }

            if (L[i][0] >= invalidMax && L[i][1] >= invalidMax) {
                System.out.println(-1);
                return;
            }
            //
            prev = arr[i];
            prevRev = rev;
        }
        System.out.println(Math.min(L[n - 1][0], L[n - 1][1]));
    }
}