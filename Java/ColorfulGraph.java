/**
 * #dfs #graph
 * #todo #dsu
 */

import java.util.Scanner;
import java.util.ArrayList;
public class ColorfulGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a-1).add(new Node(b-1, c-1));
            graph.get(b-1).add(new Node(a-1, c-1));
        }

        // query
        int q = sc.nextInt();
        for(int k = 0; k < q; k++) 
        {
            int res = 0;
            int u = sc.nextInt();
            int v = sc.nextInt();
            
            // interate each color
            for(int i = 0 ; i < m; ++i) {
                boolean[] visited = new boolean[101];
                if(dfs(u-1, i, v-1, visited, graph)) {
                    ++res;
                }
            }
            System.out.println(res);
        }
    }

    private static boolean dfs(int v, int col, int dst, boolean[] visited, ArrayList<ArrayList<Node>> graph){
        visited[v]= true;
        if(v == dst) {
            return true;
        }
        for(int i = 0; i < graph.get(v).size(); i++) {
            Node neighbour = graph.get(v).get(i);
            if(neighbour.color == col && !visited[neighbour.idx])
            {
                if(dfs(neighbour.idx, col, dst, visited, graph)) {
                    return true;
                }
            }
        }
        return false;
    }
}
class Node {
    int idx;
    int color;
    Node(int idx, int color) {
        this.idx = idx;
        this.color = color;
    }
}