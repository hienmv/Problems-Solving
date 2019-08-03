/** https://codeforces.com/problemset/problem/217/A
 *  idea: DSU or DFS/BFS
 */
 
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;

public class IceSkating {
    public static void makeSet(int[] parent, int[] rank) {
        for (int i=0; i < parent.length; i++) {
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

        if (rank[up] > rank[vp]) {
            parent[vp] = up;
        } else if (rank[up] < rank[vp]) {
            parent[up] = vp;
        } else {
            parent[up] = vp;
            rank[vp] += 1;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<Integer, ArrayList<Node>> treeNodeX = new TreeMap<>();
        TreeMap<Integer, ArrayList<Node>> treeNodeY = new TreeMap<>();
        ArrayList<Node> arr = new ArrayList<>();
        TreeMap<Node, Integer> mapNode = new TreeMap<>();

        int n = sc.nextInt();
        int[] parent = new int[n];
        int[] rank = new int[n];
        makeSet(parent, rank);

        int x, y;
        Node node;
        for (int i=0; i < n; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            node = new Node(x, y);
            if (!mapNode.containsKey(node)) {
                arr.add(node);
                mapNode.put(node, arr.size() - 1);
            }
            if (!treeNodeX.containsKey(x)) {
                treeNodeX.put(x, new ArrayList<Node>());
            }
            if (!treeNodeY.containsKey(y)) {
                treeNodeY.put(y, new ArrayList<Node>());
            }
            treeNodeX.get(x).add(node);
            treeNodeY.get(y).add(node);
           
        }
        
        int ans = n-1;
        for (int key : treeNodeX.keySet()) {
            ArrayList<Node> arrNode = treeNodeX.get(key);
            if (arrNode.size() > 1) {
                for (int i=1; i < arrNode.size(); i++) {
                    if(unionSet(mapNode.get(arrNode.get(i-1)), mapNode.get(arrNode.get(i)), parent, rank)) {
                        ans -= 1;
                    }
                }
            }
        }
        for (int key : treeNodeY.keySet()) {
            ArrayList<Node> arrNode = treeNodeY.get(key);
            if (arrNode.size() > 1) {
                for (int i=1; i < arrNode.size(); i++) {
                    if(unionSet(mapNode.get(arrNode.get(i-1)), mapNode.get(arrNode.get(i)), parent, rank)) {
                        ans -= 1;
                    }
                }
            }
        }
 
        System.out.println(ans > 0 ? ans : 0);
    }
}

class Node implements Comparable<Node> {
    int x, y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int compareTo(Node other) {
        if (this.x != other.x) {
            return this.x - other.x;
        } else {
            return this.y - other.y;
        }
    }
}