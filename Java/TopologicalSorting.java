/** 
 * idea: Topo Sorting (Kahn)
 */

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.ArrayList;

class TopologicalSorting {
    public static boolean toposorting(ArrayList<ArrayList<Integer>> graph, int[] inDegree, ArrayList<Integer> resultArr) {

        PriorityQueue<Integer> zeroInDegree = new PriorityQueue<>();
        for (int i=1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                zeroInDegree.add(i);
            }
        }

        while (!zeroInDegree.isEmpty()) {
            int node = zeroInDegree.poll();
            resultArr.add(node);

            for ( int neighbour : graph.get(node)) {
                inDegree[neighbour] -= 1;
                if (inDegree[neighbour] == 0) {
                    zeroInDegree.add(neighbour);
                }
            }
        }

        boolean ret = true;
        for (int degree : inDegree) {
            if (degree != 0) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int sz = n+1;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i < sz; i++) {
            graph.add(new ArrayList<Integer>());
        }
        int[] inDegree = new int[sz];
        ArrayList<Integer> resultArr = new ArrayList<>();

        int u, v;
        for (int i=0; i < m; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            graph.get(u).add(v);
            inDegree[v] += 1;
        }

        if (toposorting(graph, inDegree, resultArr)) {
            resultArr.forEach(x -> System.out.print(x + " "));
        } else {
            System.out.println("Sandro fails.");
        }
    }
}