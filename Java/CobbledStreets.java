/** https://www.spoj.com/problems/CSTREET/
 *  idea: Prim algorithm
 */
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.ArrayList;

class CobbledStreets {
    public static void Prim(ArrayList<ArrayList<Node>> graph, long[] distArr, int src) {
        int sz = graph.size();
        boolean[] visitedArr = new boolean[sz];
        Arrays.fill(visitedArr, false);
        distArr[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, 0));
        Node node;
        int id;
        long w;
        while (!pq.isEmpty()) {
            node = pq.poll();
            id = node.id;
            visitedArr[id] = true;
            for (Node neighbour : graph.get(id)) {
                w = neighbour.dist;
                if (!visitedArr[neighbour.id] && w < distArr[neighbour.id]) {
                    distArr[neighbour.id] = w;
                    pq.add(new Node(neighbour.id, w));
                }
            }
        }
    }
    
    public static long getPrice(long[] distArr, long price) {
        long ans = 0;
        for (long val : distArr) {
            ans += val;
        }
        return ans * price;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        long p, c;
        int n, m, a, b;
        for (int t=0; t < testcases; t++) {
            p = sc.nextLong();
            n = sc.nextInt();
            m = sc.nextInt();
            
            int sz = n+1;
            ArrayList<ArrayList<Node>> graph = new ArrayList<>();
            for(int i=0; i < sz; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i=0; i < m; i++) {
                a = sc.nextInt();
                b = sc.nextInt();
                c = sc.nextLong();
                graph.get(a).add(new Node(b,c));
                graph.get(b).add(new Node(a,c));
            }
            long[] distArr = new long[sz];
            Arrays.fill(distArr, Long.MAX_VALUE);
            distArr[0] = 0;
            Prim(graph, distArr, 1);
            
            System.out.println(getPrice(distArr, p));
        }
    }
}

class Node implements Comparable<Node> {
    int id;
    long dist;
    public Node (int id, long dist) {
        this.id = id;
        this.dist = dist;
    }

    public int compareTo(Node other) {
        return (int)(this.dist - other.dist);
    }
}