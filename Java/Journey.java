/** https://codeforces.com/problemset/problem/721/C
 * dist[x][y]:
 *  min time travel from 1 to vertex x and length path = y

 *  find max y: dist[n][y] <= T
 *
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
public class Journey {
    static class Node {
        int id;
        int dist;
        Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }
    private static void BFS(ArrayList<ArrayList<Node>> graph, int[][] dp, int[][] path, int limit) {
        int n = graph.size() - 1;
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], limit + 1);
            Arrays.fill(path[i], -1);
        }
        Arrays.fill(dp[1], 0);
        Queue<Node> q = new LinkedList<Node>();
        q.add(new Node(1, 1));
        while(!q.isEmpty()) {
            Node node = q.poll();
            int id = node.id;
            int len = node.dist + 1;
            if (len > n) continue;
            for (Node neighbor : graph.get(id)) {
                int newVal = neighbor.dist + dp[id][len - 1];
                if (newVal > limit) continue;
                if (newVal < dp[neighbor.id][len]) {
                    dp[neighbor.id][len] = newVal;
                    path[neighbor.id][len] = id;
                    q.add(new Node(neighbor.id, len));
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int t = sc.nextInt();
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int d = sc.nextInt();
            graph.get(u).add(new Node(v, d));
        }

        // dp[x][y] : min time travel from 1 to vertex x and length path = y
        int[][] dp = new int[n+1][n+1];
        int[][] path = new int[n+1][n+1];
        BFS(graph, dp, path, t);

        int number = 0;
        for (int i = 1; i <= n; i++) {
            if (dp[n][i] <= t) {
                number = i;
            }
        }
        int id = n;
        int cnt = number;
        int[] result = new int[cnt + 1];
        while(cnt > 0 && id != -1) {
            result[cnt] = id;
            id = path[id][cnt];
            cnt--;
        }
        System.out.println(number);
        for (int i = 1; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}

