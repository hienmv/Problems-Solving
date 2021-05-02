/** https://www.spoj.com/problems/TRVCOST/
 *  #graph #dijkstra
 */
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;

class TravellingCost {
    public static void Dijkstra(int rootNode, HashMap<Integer, ArrayList<Node>>graph, int[] dist, int[] path) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        dist[rootNode] = 0;
        pq.add(new Node(rootNode, 0));

        while(!pq.isEmpty()) {
            Node top = pq.poll();
            int u = top.id;
            int w = top.dist;
            for (int i=0; graph.containsKey(u) && i < graph.get(u).size(); i++) {
                Node neighbor = graph.get(u).get(i);
                if (w + neighbor.dist < dist[neighbor.id]) {
                    dist[neighbor.id] = w + neighbor.dist;
                    pq.add(new Node(neighbor.id, dist[neighbor.id]));
                    path[neighbor.id] = u;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, ArrayList<Node>> graph = new HashMap<>();
        for (int i=0; i < n; i++ ) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            if (!graph.containsKey(a)) {
                graph.put(a, new ArrayList<>());
            }
            if (!graph.containsKey(b)) {
                graph.put(b, new ArrayList<>());
            }
            graph.get(a).add(new Node(b, w));
            graph.get(b).add(new Node(a, w));
        }

        int rootNode = sc.nextInt();

        int q = sc.nextInt();
        int[] travelNodeArr = new int[q];
        for (int i=0; i < q; i++) {
            int tmp = sc.nextInt();
            travelNodeArr[i] = tmp;
        }
        int[] dist = new int[501];
        int[] path = new int[501];
        for (int i=0; i < 501; i++) {
            dist[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
        // calculate minimum cost
        Dijkstra(rootNode, graph, dist, path);

        for (int i=0; i < travelNodeArr.length; i++) {
            if (dist[travelNodeArr[i]] == Integer.MAX_VALUE) {
                System.out.println("NO PATH");
            } else {
                System.out.println(dist[travelNodeArr[i]]);
            }
        }
    }
}

class Node implements Comparable<Node> {
    Integer id;
    Integer dist;
    Node(Integer id, Integer dist) {
        this.id = id;
        this.dist = dist;
    }
    public int compareTo( Node other) {
        return this.dist.compareTo(other.dist);
    }
}