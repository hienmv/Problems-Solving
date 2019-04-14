/** https://codeforces.com/problemset/problem/580/C
 *  id: use BFS
 */

/*
            1
            2
            3
            .
            .
            .
            n/2
    /     /      \
n/2+1  n/2+2 ..... n
*/
import java.util.Deque;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;

public class KefaAndPark {

    static void BFS(ArrayList<ArrayList<Integer>> graph, ArrayList<Boolean> visitedArr, int[] catArr, int m) {
        int rootNode = 1;
        int[] dist = new int[catArr.length + 1];
        dist[rootNode] = catArr[1];
        int result = 0;
        Deque<Integer> queue = new LinkedList<>();
        queue.add(rootNode);
        visitedArr.set(rootNode,true);
        while(!queue.isEmpty()) {
            int node = queue.pollFirst();
            // leaf node
            if (graph.get(node).size() == 1 && node != rootNode) {
                result += 1;
            }
            for ( int otherNode : graph.get(node)) {
                if (!visitedArr.get(otherNode)) {
                    visitedArr.set(otherNode, true);
                    if (catArr[otherNode] == 1) {
                        dist[otherNode] = dist[node] + 1;
                    } else {
                        dist[otherNode] = 0;
                    }
                    if (dist[otherNode] <= m) {
                        queue.addLast(otherNode);
                    }
                }
            }
        }
        System.out.println(result);
    }
  
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[] catArr = new int[n+1];
        for (int i=1; i < catArr.length; i++) {
            int temp = sc.nextInt();
            catArr[i] = temp;
        }

        ArrayList<ArrayList<Integer>> graph  = new ArrayList<>(n+1);
        ArrayList<Integer> path = new ArrayList<>(n+1);
        ArrayList<Boolean> visitedNode = new ArrayList<>(n+1);
        for (int i=0; i < n+1; i++) {
            graph.add(new ArrayList<Integer>());
            path.add(-1);
            visitedNode.add(false);
        }
        for(int i=0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        } 
        BFS(graph, visitedNode, catArr, m);
    }
}