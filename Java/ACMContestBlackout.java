/** https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1541
 *  idea: run Prim algorithms double times
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class ACMContestBlackout {
    public static void Prim(ArrayList<ArrayList<Node>> graph, int[] costArr, int[] pathArr, int src) {
        int sz = graph.size();
        boolean[] visitedArr = new boolean[sz];
        Arrays.fill(visitedArr, false);
        costArr[src] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node node;
        int id, w;
        pq.add(new Node(src, 0));
        while (!pq.isEmpty()) {
            node = pq.poll();
            id = node.id;
            visitedArr[id] = true;
            for (Node neighbour : graph.get(id)) {
                w = neighbour.cost;
                if (!visitedArr[neighbour.id] && w < costArr[neighbour.id]) {
                    costArr[neighbour.id] = w;
                    pathArr[neighbour.id] = id;
                    pq.add(new Node(neighbour.id, w));
                }
            }
        }
    }

    public static long getCost(int[] costArr) {
        long ans = 0;
        for (int cost : costArr) {
            ans += cost;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        int n, m, a, b, c;
        for (int t=0; t < testcases; t++) {
            n = sc.nextInt();
            m = sc.nextInt();
            ArrayList<ArrayList<Node>> graph = new ArrayList<>();
            int sz = n+1;
            for (int i=0; i < sz; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i=0; i < m; i++) {
                a = sc.nextInt();
                b = sc.nextInt();
                c = sc.nextInt();
                graph.get(a).add(new Node(b, c));
                graph.get(b).add(new Node(a, c));
            }

            // first time
            int[] costArr1 = new int[sz];
            int[] pathArr1 = new int[sz];
            Arrays.fill(pathArr1, -1);
            Arrays.fill(costArr1, Integer.MAX_VALUE);
            costArr1[0] = 0;
            Prim(graph, costArr1, pathArr1, 1);
            int val1 = (int)getCost(costArr1);
            
            // second time
            int cost_tmp = -1;
            int val2 = Integer.MAX_VALUE;
            int[] pathArr2 = new int[sz];
            Arrays.fill(pathArr2, -1);
            int[] costArr2 = new int[sz];
            for (int id = 1; id < sz; id++) {
               
                Arrays.fill(costArr2, Integer.MAX_VALUE);
                costArr2[0] = 0;
            
                if (pathArr1[id] != -1) {
                    cost_tmp = costArr1[id];
                    for (Node node : graph.get(id)) {
                        if (node.id == pathArr1[id]) {
                            node.cost = Integer.MAX_VALUE;
                        }
                    }
                    for (Node node : graph.get(pathArr1[id])) {
                        if (node.id == id) {
                            node.cost = Integer.MAX_VALUE;
                        }
                    }

                    Prim(graph, costArr2, pathArr2, 1);
                    val2 = (int)Math.min(getCost(costArr2), val2);
                    
                    for (Node node : graph.get(id)) {
                        if (node.id == pathArr1[id]) {
                            node.cost = cost_tmp;
                        }
                    }
                    for (Node node : graph.get(pathArr1[id])) {
                        if (node.id == id) {
                            node.cost = cost_tmp;
                        }
                    }
                }
            }
            System.out.println(val1 + " " + val2);
        }
    }

}

class Node implements Comparable<Node> {
    int id, cost;
    public Node(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}