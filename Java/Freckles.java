/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=975
 *  #dsu #mst
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
 
public class Freckles {
    public static void makeSet(int[] parent, int[] rank) {
        int sz = parent.length;
        for (int i=0; i < sz; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    public static int findSet(int u, int[] parent) {
        if (parent[u] != u) {
            parent[u] = findSet(parent[u], parent);
        }
        return parent[u];
    }
    public static boolean unionSet(int u, int v, int[] parent, int[] rank) {
        int up = findSet(u, parent);
        int vp = findSet(v, parent);
        if (up == vp) {
            return false;
        }
        if (rank[up] < rank[vp]) {
            parent[up] = vp;
        } else if (rank[up] > rank[vp]) {
            parent[vp] = up;
        } else {
            parent[vp] = up;
            rank[up] += 1;
        }
        
        return true;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();

        for (int t=0; t < testcases; t++) {
            if (t > 0) {
                System.out.println();
            }

            int n = sc.nextInt();
            double x, y;
            Node[] arrNode = new Node[n];
            for (int i=0; i < n; i++) {
                x = sc.nextDouble();
                y = sc.nextDouble();
                arrNode[i] = new Node(x,y);
            }

            ArrayList<Edge> arrEdge = new ArrayList<>();
            double length = 0.0;
            for (int i=0; i < n-1; i++) {
                for (int j=i+1; j < n; j++) {
                    length = Math.pow(arrNode[i].x - arrNode[j].x, 2) + Math.pow(arrNode[i].y - arrNode[j].y, 2);
                    arrEdge.add(new Edge(i, j, length));
                }
            }

            Collections.sort(arrEdge);

            double totalLength = 0.0;

            int numSelectedEdge = 0;
            int[] parent = new int[n];
            int[] rank = new int[n];
            makeSet(parent, rank);
     
            for (Edge edge : arrEdge) {
                if (unionSet(edge.u, edge.v, parent, rank)) {
                    totalLength += Math.sqrt(edge.w);
                    numSelectedEdge += 1;
                }
                if (numSelectedEdge == n - 1) {
                    break;
                }
            }
     
            System.out.println(String.format("%.2f", totalLength));
        }
    }
}

class Node {
    double x, y;
    Node(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge> {
    int u, v;
    double w;
    public Edge (int u, int v, double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
 
    public int compareTo(Edge other) {
        if (this.w == other.w) {
            return 0;
        } else if (this.w > other.w) {
            return 1;
        } else {
            return -1;
        }
    }
}