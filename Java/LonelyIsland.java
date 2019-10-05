/** https://www.hackerearth.com/practice/algorithms/graphs/topological-sort/practice-problems/algorithm/lonelyisland-49054110/
 *  #topological-sort
 */
/*
    0 -> 1
    0 -> 3
    1 -> 2
    2 -> 5
    3 -> 4
    5 -> 4
    
    [0]
    [1, 3]
    [3, 2]
    [2, 4]
    [4, 5]
    
         4
      / |
    1   |
     \ |
      3* 1
     /
    2
*/

import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class LonelyIsland {
    public static void topoSort(ArrayList<ArrayList<Integer>> graph, int[] inDegree, double[] probabilitiesArr) {
        
        int sz = graph.size();
        boolean[] visitedArr = new boolean[sz];
        Deque<Integer> zeroInDegree = new LinkedList<>();
        for (int i=1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) { 
                zeroInDegree.addLast(i);
            }
        }

        while(!zeroInDegree.isEmpty()) {
            int node = zeroInDegree.pollFirst();
            visitedArr[node] = true;
            int div = graph.get(node).size();
            for (int neighbour : graph.get(node)) {
                if (!visitedArr[neighbour]) {
                    inDegree[neighbour] -= 1;
                    if (inDegree[neighbour] == 0) {
                        zeroInDegree.addLast(neighbour);
                    }
                    probabilitiesArr[neighbour] += probabilitiesArr[node] / div;
                }
            }
        }
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int startNode = sc.nextInt();

        int sz = n+1;
        double[] probabilitiesArr = new double[sz];
        probabilitiesArr[startNode] = 1.0f;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[sz];
        for (int i=0; i < sz; i++) {
            graph.add(new ArrayList<>());
        }
        int u, v;
        for (int i=0; i < m; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            graph.get(u).add(v);
            inDegree[v] += 1;
        }

        topoSort(graph, inDegree, probabilitiesArr);
        double maxProbility = Double.MIN_VALUE;
        for (int i=1; i < probabilitiesArr.length; i++) {
            if (graph.get(i).isEmpty()) {
                maxProbility = Math.max(maxProbility, probabilitiesArr[i]);
            }
        }
        
        for (int idx=1; idx < probabilitiesArr.length; idx++) {
            if (maxProbility - probabilitiesArr[idx] <= 1e-9 && graph.get(idx).isEmpty()) {
                System.out.print(idx + " ");
            }
        }
        System.out.println();

        return;
    }
}