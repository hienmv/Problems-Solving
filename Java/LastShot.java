/** https://www.spoj.com/problems/LASTSHOT/
 * idea: dfs
 */
/**
 *  1 3
 *  1 4
 *  4 5
 *  2 1
 *  expected output: 5
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class LastShot {
    static int getImpact(int vertex, ArrayList<ArrayList<Integer>> graph, int totalVertex) {
        boolean[] visitedArr = new boolean[totalVertex];
        int count = 0;
        Deque<Integer> queue = new LinkedList<>();
        visitedArr[vertex] = true;
        queue.addLast(vertex);
        while(!queue.isEmpty()) {
            count++;
            int node = queue.pollLast();
            for (int childNode : graph.get(node)) {
                if (!visitedArr[childNode]) {
                    visitedArr[childNode] = true;
                    queue.addLast(childNode);
                }
            }
        }

        return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n+1);
        for (int i=0; i < n+1; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i=0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
        }
       
        int maxImpact = 1;
        for (int node=1; node < graph.size(); node++) {
            int count = getImpact(node, graph, n+1);
            if (count > maxImpact) {
                maxImpact = count;
            }
        }
        System.out.println(maxImpact);
    }
}