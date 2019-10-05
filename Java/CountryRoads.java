/** http://www.lightoj.com/volume_showproblem.php?problem=1002
 *  #dijkstra #shortest-path
 * */

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;

class CountryRoads {
    private static ArrayList<Node>[] graph = new ArrayList[501];
    private static int[] distArr = new int[501];
    public static void Dijkstra(int startVertex) {
        
        distArr[startVertex] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startVertex, 0));
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int id = node.id;
            int dist = node.dist;
            
            if (dist > distArr[id]) {
                continue;
            }
            for(Node neighbour : graph[id]) {
                int newDist = Math.max(dist, neighbour.dist);
                if (newDist < distArr[neighbour.id]) {
                    distArr[neighbour.id] = newDist;
                    pq.add(new Node(neighbour.id, newDist));
                }
            }
        }
    }

    public static void main(String[] args) {
        MyScanner sc = new MyScanner(System.in);
        int n, m, u, v, w;
        int testCases = sc.nextInt();
        for (int testcase=1; testcase < testCases+1; testcase++) {
            n = sc.nextInt();
            m = sc.nextInt();
            for (int i=0; i < n; i++) {
                graph[i] = new ArrayList<Node>();
                distArr[i] = Integer.MAX_VALUE;
            }
            for(int i=0; i < m; i++) {
                u = sc.nextInt();
                v = sc.nextInt();
                w = sc.nextInt();
                graph[u].add(new Node(v, w));
                graph[v].add(new Node(u, w));
            }
            int startVertex = sc.nextInt();
            Dijkstra(startVertex);
            System.out.println("Case " + testcase + ":");
            for (int i = 0; i < n; i++) {
                if(distArr[i] == Integer.MAX_VALUE) {
                    System.out.println("Impossible");
                } else {
                    System.out.println(distArr[i]);
                }
            }
        }

    }
}


class Node implements Comparable<Node> {
   
    int id;
    int dist;
    Node(int id, int dist) {
        this.id = id;
        this.dist = dist;
    }
    public int compareTo(Node other) {
        return this.dist - other.dist;
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