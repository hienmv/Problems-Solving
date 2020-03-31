/** https://codeforces.com/problemset/problem/505/C
 * #bfs #dp
 */
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.*;
/* Bottom Up - take time, rework
public class TreasureHunter {
    private static class Node {
        int idx, jump;
        Node(int idx, int jump) {
            this.idx = idx;
            this.jump = jump;
        }
    }
    public static void main(String[] args) {
        MyScanner sc = new MyScanner(System.in);
        int max = 30001;
        int n = sc.nextInt();
        int d = sc.nextInt();
        int[] arr = new int[max];
        int last_pos = 0;
        for (int i = 0; i < n; i++){
            last_pos = sc.nextInt();
            arr[last_pos] += 1;
        }
        max = last_pos + 1;
        int max_jump = d + n;
/* Find max position that can be reached.
        // d = 1
        // 0, d, d+1, d+2, d+3, ... >= MAX_N
        // d + d+1 + d+2 + ... + d+k >= MAX_N
        // (k+1)*d + k*(k+1)/2 >= MAX_N
        // k+1 + k(k+1)/2 >= MAX_N
        // k^2 + 3k + 2 => 2 * MAX_N = 60000
        // k(k + 1) + 2(k + 1)
        // (k+1) * (k+2) >= 60000
        // => k = 245
        
        // min_jump = d - 245 => 0
        // max_jump = d + 245 => 500
        => max_jump = 500
 *//*
        max_jump = 500;
        int[][] gems = new int[max][max_jump];
        for(int[] gems_pos : gems) {
            Arrays.fill(gems_pos, -1);
        }

        int result = gems[d][d % max_jump] = arr[d];
        int[] slices = {-1, 0, 1};
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(d, d));
        while(!q.isEmpty()) {
            Node node = q.poll();
            for (int slice : slices) {
                int jump = slice + node.jump;
                int next = node.idx + jump;
                if (jump > 0 && next < max) {
                    if (arr[next] + gems[node.idx][node.jump % max_jump] > gems[next][jump % max_jump]) {
                        gems[next][jump % max_jump] = arr[next] + gems[node.idx][node.jump % max_jump];
                        if (result < gems[next][jump % max_jump]) {
                            result = gems[next][jump % max_jump];
                        }
                        q.add(new Node(next, jump));
                    }
                }
            }
        }

        System.out.println(result);
    }
}

class MyScanner {
    BufferedReader br;
    StringTokenizer st;

    public MyScanner(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }
    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt() {
        return Integer.parseInt(next());
    }
    long nextLong() {
        return Long.parseLong(next());
    }
    double nextDouble() {
        return Double.parseDouble(next());
    }
    String nextLine() {
        String str = "";
        try {
                str = br.readLine();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return str;
    }
}

*/
// Top down
/**
/*
dp[i][j]: max gem from n to i, last jump j
    -> dp[i+j-1][j-1]   if i+j-1 <= n
    -> dp[i+j  ][j  ]   if i+j   <= n
    -> dp[i+j+1][j+1]   if i+j+1 <= n
    dp(i, j) = arr[i] + max(dp(i+j-1, j-1), dp(i+j, j), dp(i+j+1, j+1))

dp(d, d)

DP top-down
function dp(int i, int j):
  if f[i][j] != -1:
    return f[i][j]
  if i == n:
    return f[i][j] = arr[i]
  f[i][j] = 0
  if step j-1 is valid:
    f[i][j] = max(f[i][j], arr[i] + dp(i+j-1, j-1))
  if step j is valid:
    f[i][j] = max(f[i][j], arr[i] + dp(i+j, j))
  if step j-1 is valid:
    f[i][j] = max(f[i][j], arr[i] + dp(i+j+1, j+1))
  return f[i][j]
*/
public class TreasureHunter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max = 30001;
        int n = sc.nextInt();
        int d = sc.nextInt();
        int[] arr = new int[max];
        int last_pos = 0;
        for (int i = 0; i < n; i++){
            last_pos = sc.nextInt();
            arr[last_pos] += 1;
        }
        int max_jump = 500;
        int[][] gems = new int[last_pos+1][max_jump];
        for(int[] gems_pos : gems) {
            Arrays.fill(gems_pos, -1);
        }
        int result = dp(d, d, d, gems, arr);
        System.out.println(result);
    }
    // d + 250                  d           <=>     250             (d-250)
    // j                       j-d+250      
    // -250
    private static int dp(int i, int j, int d, int[][] gems, int[] arr) {
        int n = gems.length;
        int jump = j - d + 250;
        if (gems[i][jump] != -1) {
            return gems[i][jump];
        }
        if (i == n - 1) {
            gems[i][jump] = arr[i];
            return gems[i][jump];
        }
        // initialize
        gems[i][jump] = 0;

        // j - 1
        if (i + j - 1 < n && j - 1 > 0 && Math.abs(j - d) < 250) {
            gems[i][jump] = Math.max(gems[i][jump], dp(i + j - 1, j - 1, d, gems, arr));
        }
        // j
        if (i + j < n && j > 0 && Math.abs(j - d) < 250) {
            gems[i][jump] = Math.max(gems[i][jump], dp(i + j, j, d, gems, arr));
        }
        // j + 1
        if (i + j + 1 < n && j + 1 > 0 && Math.abs(j - d) < 250) {
            gems[i][jump] = Math.max(gems[i][jump], dp(i + j + 1, j + 1, d, gems, arr));
        }

        gems[i][jump] += arr[i];

        return gems[i][jump];
    }
}