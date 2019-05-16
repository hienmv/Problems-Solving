/** https://www.hackerearth.com/practice/algorithms/graphs/shortest-path-algorithms/practice-problems/algorithm/successful-marathon-0691ec04/description/
 *  idea: use dijkstra with start vertex: source, destination 
 *  and get min dist from them to vertexes containing chocolate.
 *  Notice: 
 *     - wrapper classes and primitive types. (Integer and int) 
 *     - overflow problem.
 */

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

class ChocolateJourney {

    static int max_val = (int)1e9; // Integer.MAX_VALUE -> cause overflow problem.

    public static int[] Dijkstra( ArrayList<Node>[] graph, int startVertex) {
        int len = graph.length;
        int[] distArr = new int[len];
        Arrays.fill(distArr, max_val);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distArr[startVertex] = 0;
        pq.add(new Node(startVertex, distArr[startVertex]));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int id = node.id;
            int w = node.dist;
            // reduce loop times
            if (w > distArr[id]) {
                continue;
            }
            for (Node neighbour : graph[id]) {
                if (w + neighbour.dist < distArr[neighbour.id]) {
                    distArr[neighbour.id] = w + neighbour.dist;
                    pq.add(new Node(neighbour.id, distArr[neighbour.id]));
                }
            }
        }

        return distArr;
    }
    public static void main (String[] args) {
        
        MyScanner sc = new MyScanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();
        int k = sc.nextInt();
        int x = sc.nextInt();
        int[] chocolateVertexArr = new int[k];
        for (int i=0; i < k; i++) {
            int tmp = sc.nextInt();
            chocolateVertexArr[i] = tmp;
        }
        
        ArrayList<Node>[] graph = new ArrayList[n+1];
        for (int i=0; i < graph.length; i++) {
            graph[i] = new ArrayList<Node>();
        }
        for (long i=0; i < m; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int dist = sc.nextInt();
            graph[v1].add(new Node(v2, dist));
            graph[v2].add(new Node(v1, dist));
        }
        int source = sc.nextInt();
        int destination = sc.nextInt();
        int[] distSourceArr = Dijkstra(graph, source);
        int[] distDestinationArr =  Dijkstra(graph, destination);
        long result = max_val;
        for (int vertex : chocolateVertexArr) {
            if (distDestinationArr[vertex] <= x) {
                if (distDestinationArr[vertex] + distSourceArr[vertex] < result) {
                    result = distDestinationArr[vertex] + distSourceArr[vertex];
                }
            }
        }
        System.out.println( result >= max_val ? (-1) : result);
    }
}

class Node implements Comparable<Node> {
    int id;
    int dist;
    Node(int id, int dist) {
        this.id = id;
        this.dist = dist;
    }
    @Override
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