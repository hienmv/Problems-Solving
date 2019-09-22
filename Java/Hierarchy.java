/**
* idea: Topo sorting, add edge
    2 1 3 4 
    -> result: ith depends on previous element. (i-1 th).
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class Hierarchy {
    public static void topoSorting(ArrayList<ArrayList<Integer>> graph, int[] inDegree, ArrayList<Integer> result) {
        Deque<Integer> zeroInDegree = new LinkedList<>();
        for (int i=1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                zeroInDegree.addLast(i);
            }
        }

        while (!zeroInDegree.isEmpty()) {
            int node = zeroInDegree.poll();
            result.add(node);

            for (int neighbour : graph.get(node)) {
                inDegree[neighbour] -= 1;
                if (inDegree[neighbour] == 0) {
                    zeroInDegree.addLast(neighbour);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int sz = n+1;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i < sz; i++) {
            graph.add(new ArrayList<>());
        }
        int node;
        int[] inDegree = new int[sz];
        for (int i=1; i < k+1; i++) {
            int num = sc.nextInt();
            for (int j=0; j < num; j++) {
                node = sc.nextInt();
                graph.get(i).add(node);
                inDegree[node] += 1;
            }
        }
        ArrayList<Integer> topoSortOrder = new ArrayList<>();
        topoSorting(graph, inDegree, topoSortOrder);

        int[] result = new int[sz];
        result [topoSortOrder.get(0)] = 0;
        for (int i=1; i < topoSortOrder.size(); i++) {
            result[topoSortOrder.get(i)] = topoSortOrder.get(i-1);
        }
        for (int i=1; i < result.length; i++) {
            System.out.println(result[i]);
        }


    }
}