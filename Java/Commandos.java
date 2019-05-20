/** http://www.lightoj.com/volume_showproblem.php?problem=1174
 *  idea: use dijkstra from each vertex to S and T. 
 *  get maximum of each cost of path S-> T via each vertex
 *  <<break down the path>>
 */

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;

class Commandos {

    static void Dijkstra(ArrayList<Node>[] graph, int source, int[] distArr) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distArr[source] = 0;
        pq.add(new Node(source, distArr[source]));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int id = node.id;
            int w = node.dist;

            for (Node neighbour : graph[id]) {
                if (w + neighbour.dist < distArr[neighbour.id]) {
                    distArr[neighbour.id] = w + neighbour.dist;
                    pq.add(new Node(neighbour.id, distArr[neighbour.id]));
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for (int t=1; t < testCases + 1; t++) {
            int n = sc.nextInt();
            int r = sc.nextInt();
            ArrayList<Node>[] graph = new ArrayList[n];
            for (int i=0; i < n; i++) {
                graph[i] = new ArrayList<Node>();
            }
            for (int i=0; i < r; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                graph[u].add(new Node(v, 1));
                graph[v].add(new Node(u, 1));
            }
            
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int[] distArrS = new int[n];
            int[] distArrT = new int[n];
            Arrays.fill(distArrS, n);
            Arrays.fill(distArrT, n);

            Dijkstra(graph, source, distArrS);
            Dijkstra(graph, destination, distArrT);

            int result = 0;
            for (int i=0; i < n; i++) {
                result = Math.max(distArrS[i] + distArrT[i], result);
            }
            
            System.out.println("Case " + t + ": " + result);
        }
    }
}

class Node implements Comparable<Node> {
    int id;
    int dist;
    Node(int _id, int _dist) {
        this.id = _id;
        this.dist = _dist;
    }
    public int compareTo(Node other) {
        return this.dist - other.dist;
    }
}