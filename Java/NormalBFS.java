/** https://www.hackerrank.com/challenges/bfsshortreach/problem
 * #bfs #shortest-path
 */

import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

public class NormalBFS {
    static void BFS(int startNode, ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> path,
            ArrayList<Boolean> visited) {
        Deque<Integer> queue = new LinkedList<Integer>();
        queue.addLast(startNode);
        visited.set(startNode, true);
        while(!queue.isEmpty()) {
            int node = queue.pollFirst();
            for(int i: graph.get(node)) {
                if (visited.get(i) == false) {
                    path.set(i, node);
                    visited.set(i, true);
                    queue.addLast(i);
                }
            }
        }
    }

    static int getVisitedNode(int s, int f, ArrayList<Integer> path) {
        if (s == f) {
            return 0;
        }
        if (path.get(f) == -1) {
            return 0;
        } else {
            return 1 + getVisitedNode(s, path.get(f), path);
        }
    }

    static String getResult(int startNode, int targetNode, ArrayList<Integer> path) {
        String rs = "";
        if (startNode != targetNode) {
            int temp = getVisitedNode(startNode, targetNode, path);
            rs = String.valueOf((temp != 0) ? (temp * 6) : -1) + " ";
        }
        return rs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        ArrayList<String> rt = new ArrayList<>();

        for (int k=0; k < q; k++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            ArrayList<Integer> path = new ArrayList<>(n+1);
            ArrayList<Boolean> visited = new ArrayList<>(n+1);
            ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(n+1);

            for (int i=0; i < n+1; ++i) {
                graph.add(new ArrayList<Integer>());
                path.add(-1);
                visited.add(false);
            }
            for (int i=0; i < m; ++i) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            int startNode = sc.nextInt();
            BFS(startNode, graph, path, visited);
            StringBuilder sb = new StringBuilder();
            for (int i=1; i < n+1; ++i){
                sb.append(getResult(startNode, i, path));
            }
            rt.add(sb.toString());
        }
        for (String s: rt) {
            System.out.println(s);
        }

    }
}