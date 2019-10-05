/**
 * #bfs
 */
import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;

public class ShortedBFS {
    static void BFS(int startNode, ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> path,
            ArrayList<Boolean> visited, ArrayList<Integer> result) {
        Deque<Integer> queue = new LinkedList<Integer>();
        queue.addLast(startNode);
        visited.set(startNode, true);
        result.set(startNode, 0);
        while(!queue.isEmpty()) {
            int node = queue.pollFirst();
            for(int i: graph.get(node)) {
                if (visited.get(i) == false) {
                    path.set(i, node);
                    visited.set(i, true);
                    result.set(i, result.get(node) + 6);
                    queue.addLast(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        ArrayList<String> rt = new ArrayList<>();

        for (int k=0; k < q; k++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            ArrayList<Integer> path = new ArrayList<>(n+1);
            ArrayList<Integer> result = new ArrayList<>(n+1);
            ArrayList<Boolean> visited = new ArrayList<>(n+1);
            ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(n+1);

            for (int i=0; i < n+1; ++i) {
                graph.add(new ArrayList<Integer>());
                path.add(-1);
                visited.add(false);
                result.add(-1);
            }
            for (int i=0; i < m; ++i) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            int startNode = sc.nextInt();
            BFS(startNode, graph, path, visited, result);
            StringBuilder sb = new StringBuilder();
            for (int i=1; i < result.size(); ++i) {
                if (result.get(i) != 0) {
                    sb.append(String.valueOf(result.get(i)) + " ");
                }
            }
            rt.add(sb.toString());
        }
        for (String s: rt) {
            System.out.println(s);
        }

    }
}