/** https://www.hackerearth.com/practice/algorithms/graphs/depth-first-search/practice-problems/algorithm/bishu-and-his-girlfriend/
 * tag: #dfs #bfs 
 * use BFS / DFS (stack/ recursion)
 */
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class BishuAndHisGirlFriend {
    static int callDFSRecursion(int startNode, ArrayList<ArrayList<Integer>> graph, boolean[] visitedArr, 
            boolean[] girlArr, HashMap<Integer, Integer> result) {
        
        int dist = 0;
        visitedArr[startNode] = true;
        if (girlArr[startNode]) {
            dist+= 1;
            if (result.containsKey(dist)) {
                if (startNode < result.get(dist)) {
                    result.replace(dist, startNode);
                }
            } else {
                result.put(dist, startNode);
            }
        }

        if (!graph.get(startNode).isEmpty()) {
            for ( int node: graph.get(startNode)) {
                if (!visitedArr[node]) {
                    dist += callDFSRecursion(node, graph, visitedArr, girlArr, result);
                }
            }
        }
        return dist;
}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n+1);
        for (int i=0; i < n+1; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i=0; i < n-1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int q = sc.nextInt();
        boolean[] girlArr = new boolean[n+1];
        for (int i=0; i < q; i++) {
            int g = sc.nextInt();
            girlArr[g] = true;
        }

        boolean[] visitedArr = new boolean[n+1];
        HashMap<Integer, Integer> result = new HashMap<>();
        callDFSRecursion(1, graph, visitedArr, girlArr, result);
        System.out.println(result.get(Collections.min(result.keySet())));

    }
}