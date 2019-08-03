/** https://www.spoj.com/problems/MST/
 *  idea: original Prim Algorithm
 */

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class MST {
    public static void Prim(int src, ArrayList<ArrayList<Node>> graph, ArrayList<Integer> distArr, 
        ArrayList<Boolean> visitedArr, ArrayList<Integer> pathArr) {
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(src,0));
        distArr.set(src, 0);

        int u, v, w;
        while(!pq.isEmpty()) {
            Node top = pq.poll();
            u = top.id;
            visitedArr.set(u, true);
            for(int i=0; i < graph.get(u).size(); i++) {
                Node neighbor = graph.get(u).get(i);
                v = neighbor.id;
                w = neighbor.dist;
                if (!visitedArr.get(v) && w < distArr.get(v)) {
                    distArr.set(v, w);
                    pq.add(new Node(v, w));
                    pathArr.set(v, u); 
                }
            }
        }
    }
    public static long weigthMST(ArrayList<Integer> distArr, ArrayList<Integer> pathArr) {
        long ans = 0;
        int n = distArr.size();
        for (int i=1; i < n; i++) {
            ans += (long)distArr.get(i);
        }

        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Node> > graph = new ArrayList<>();
        ArrayList<Integer> distArr = new ArrayList<>();
        ArrayList<Boolean> visitedArr = new ArrayList<>();
        ArrayList<Integer> pathArr = new ArrayList<>();
        for (int i=0; i < n+1; i++) {
            graph.add(new ArrayList<Node>());
            visitedArr.add(false);
            pathArr.add(-1);
            distArr.add(Integer.MAX_VALUE);
        }
        int u, v, w;
        for (int i=0; i < m; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            w = sc.nextInt();
            graph.get(u).add(new Node(v,w));
            graph.get(v).add(new Node(u,w));
        }
        Prim(1, graph, distArr, visitedArr, pathArr);
        System.out.println(weigthMST(distArr, pathArr));
    }
}

class Node implements Comparable<Node> {
    int id, dist;
    public Node(int id, int dist) {
        this.id = id;
        this.dist = dist;
    }
    public int compareTo(Node other) {
        return this.dist - other.dist;
    }
}