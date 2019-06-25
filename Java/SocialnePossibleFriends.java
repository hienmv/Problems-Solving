/** https://www.spoj.com/problems/SOCIALNE/
 *  idea: Floyd-Warshall
 */

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;

public class SocialnePossibleFriends {
    public static int INF = 9999;

    public static boolean FloydWarShall(int[][] dist) {
        int i,j,k;
        int V = dist.length;
        for (k=0; k < V; k++ ) {
            for(i=0; i < V; i++) {
                for(j=0; j < V; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // check negative-weight cycles
        for(i=0; i < V; i++) {
            for(j=0; j < V; j++) {
                if (dist[i][j] < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyScanner sc = new MyScanner(System.in);
        int testcases = sc.nextInt();
        for (int t=0; t < testcases; t++) {
            String firstString = sc.next();
            int m = firstString.length();
            char[][] graph = new char[m][m];
            graph[0] = firstString.toCharArray();
            for (int i=1; i < m; i++) {
                String tmp = sc.next();
                graph[i] = tmp.toCharArray();
            }
            int[][] dist = new int[m][m];
            for (int i=0; i < m; i++) {
                for (int j=0; j < m; j++) {
                    dist[i][j] = graph[i][j] == 'Y' ? 1 : INF;
                }
                dist[i][i] = 0;
            }
            
            FloydWarShall(dist);

            int maxFriends = -1;
            int id = -1;
            for (int i=0; i < m; i++) {
                int count = 0;
                for (int j=0; j < m; j++) {
                    if (dist[i][j] == 2) {
                        count++;
                    }
                }
                if (count > maxFriends) {
                    maxFriends = count;
                    id = i;
                }
            }
            System.out.println(id + " " + maxFriends);
        }
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