/** https://www.spoj.com/problems/RPLA/
 * tag: #topological-sort
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class AnswerBoss {
    public static void topoSorting(ArrayList<ArrayList<Integer>> graph, int[] inDegree, ArrayList<ArrayList<Integer>> rankArr) {
        for (int i=0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                rankArr.get(1).add(i);
            }
        }

        for (int rank=1; rank < rankArr.size(); rank++) {
            for (int node : rankArr.get(rank)) {
                for (int neighbour : graph.get(node)) {
                    inDegree[neighbour] -= 1;
                    if (inDegree[neighbour] == 0) {
                        rankArr.get(rank + 1).add(neighbour);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        
        for (int t=1; t < testcases+1; t++) {
            int n = sc.nextInt();
            int r = sc.nextInt();
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            ArrayList<ArrayList<Integer>> rankArr = new ArrayList<>();
            for (int i=0; i < n; i++) {
                graph.add(new ArrayList<>());
                rankArr.add(new ArrayList<>());
            }
            rankArr.add(new ArrayList<>());

            int[] inDegree = new int[n];
            int u, v;
            for (int i=0; i < r; i++) {
                u = sc.nextInt();
                v = sc.nextInt();
                graph.get(v).add(u);
                inDegree[u] += 1;
            }

            topoSorting(graph, inDegree, rankArr);
            System.out.println("Scenario #" + t + ":");
        
            for (int rank=0; rank < rankArr.size(); rank++) {
                if (rankArr.get(rank).isEmpty()) {
                    continue;
                }
                Collections.sort(rankArr.get(rank));
                for (int node : rankArr.get(rank)) {
                    System.out.println(rank + " " + node);
                }
            }
        }

        return;
    }
}