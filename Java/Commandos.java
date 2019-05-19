/** http://www.lightoj.com/volume_showproblem.php?problem=1174
 *  idea: use dijkstra from start vertex
 */

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;

class Commandos {

    static int Dijkstra(ArrayList<Node>[] graph, int source, int  destination, boolean[] visitedPath) {
        int len = graph.length;
        int[] distArr = new int[len];
        int[] path = new int[len];
        Arrays.fill(distArr, len);
        Arrays.fill(path, -1);

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
                    path[neighbour.id] = id;
                    pq.add(new Node(neighbour.id, distArr[neighbour.id]));
                }
            }
        }
       
        visitedPath[source] = true;
        visitedPath[destination] = true;
        int st = path[destination];
        while(st != source) {
            visitedPath[st] = true;
            st = path[st];
        }

        return distArr[destination];
    }
    
    // find the minimum cost from source to one of the vertexs visited.
    static int getDist(ArrayList<Node>[] graph, int source, boolean[] visitedPath) {
        int len = graph.length;
        int[] distArr = new int[len];
        int[] path = new int[len];
        Arrays.fill(distArr, len);
        Arrays.fill(path, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        distArr[source] = 0;
        pq.add(new Node(source, distArr[source]));
        
        int destination = -1; // the vertex visited with minimum cost. 
        while (!pq.isEmpty()) {
            if (destination != -1) {
                break;
            }
            Node node = pq.poll();
            int id = node.id;
            int w = node.dist;
            if (w > distArr[node.id]) {
                continue;
            }
            for (Node neighbour : graph[id]) {
                if(w + neighbour.dist < distArr[neighbour.id]) {
                    distArr[neighbour.id] = w + neighbour.dist;
                    path[neighbour.id] = id;
                    if (visitedPath[neighbour.id]) {
                        destination = id;
                        break;
                    }
                    pq.add(new Node(neighbour.id, distArr[neighbour.id]));
                }
            }
        }
        int count = 0;
        while (destination != -1 && !visitedPath[destination]) {
            visitedPath[destination] = true;
            count++;
            destination = path[destination];
        }
        return count;
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
            boolean[] visitedPath = new boolean[n];
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int result = Dijkstra(graph, source, destination, visitedPath);
            for (int i=0; i < n; i++) {
                if(visitedPath[i] == false) {
                    int count = getDist(graph, i, visitedPath);
                    result += count*2;
                }
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