/** https://codeforces.com/contest/1214/problem/D
 * #dynamic-programming #todo
 /*
    suggest: toi da can block 2 o.

    approach 1: dynamic programming
        => o bai nay khong ap dung dc cach nay.
        => se co bai can mindset nay
        ...##
        .#.##
        .....
        ##.#.
        ##...
        
        f[i][j] = so cach di tu (1,1) toi (i, j)
        g[i][j] = so cach di tu (n, m) toi (i, j)
        
        f[n][m] = so cach di tu (1, 1) toi (n, m)
        f[i][j] * g[i][j] = f[n][m]
    
    approach 2: able
        dfs/graph theory
            dfs lan 1:
                false: -> 0
                true: block path
                    dfs lan 2:
                        false -> 1
                        true -> 2
 */
import java.util.Scanner;
 public class TreasureIsland {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean[][] arr = new boolean[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String line = sc.next();
            for(int j = 1; j <= m; j++) {
                if (line.charAt(j - 1) == '.') {
                    arr[i][j] = true;
                }
            }
        }
        int[][] graph = new int[n + 1][m + 1];
        graph[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i][j]) {
                    if (arr[i - 1][j] && graph[i - 1][j] > 0) {
                        graph[i][j] += 1;
                    }
                    if (arr[i][j - 1] && graph[i][j - 1] > 0) {
                        graph[i][j] += 1;
                    }
                    if (i != 1 && j != 1 && graph[i-1][j-1] == 1 && graph[i][j] == 2) {
                        graph[i][j] = 1;
                    }
                }
            }
        }
        // for (int i = 1; i <=n; i++) {
        //     for (int j = 1 ; j<=m; j++){
        //         System.out.print(graph[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();

        int result = graph[n][m];
        if ((m > 1 && graph[1][2] == 0) || (n > 1 && graph[2][1] == 0)
            || graph[n][m-1] == 0 || graph[n - 1][m] == 0)
        {
            result = Math.min(1, result);
        }
        System.out.println(result);
    }
}