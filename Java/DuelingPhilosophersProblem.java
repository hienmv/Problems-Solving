/* https://icpcarchive.ecs.baylor.edu/index.php?option=onlinejudge&page=show_problem&problem=4206
    #topological-sort
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.HashSet;

class DuelingPhilosophersProblem {
    public static boolean topoSort(ArrayList<ArrayList<Integer>> graph, int[] inDegree, ArrayList<Integer> result) {
        int sz = graph.size();
        boolean[] visitedArr = new boolean[sz];

        Deque<Integer> zeroInDegree = new LinkedList<>();
        for (int i=1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                zeroInDegree.addLast(i);
            }
        }

        while (!zeroInDegree.isEmpty()) {
            int node = zeroInDegree.pollFirst();
            visitedArr[node] = true;
            result.add(node);
            for (int neighbour : graph.get(node)) {
                if(!visitedArr[neighbour]) {
                    inDegree[neighbour] -= 1;
                    if(inDegree[neighbour] == 0) {
                        zeroInDegree.addLast(neighbour);
                    }
                }
            }
        }
        boolean ans = true;
        for (int degree : inDegree) {
            if (degree > 0) {
                ans = false;
            }
        }

        return ans;
/**
other way:
- no need parent graph
        ans = 1;
        while (!zeroInDegree.isEmpty()) {
            // int sz = zeroInDegree.size();
            // if sz > 1: ans = 2
            int node = zeroInDegree.pollFirst();
            visitedArr[node] = true;
            result.add(node);
            for (int neighbour : graph.get(node)) {
                if(!visitedArr[neighbour]) {
                    inDegree[neighbour] -= 1;
                    if(inDegree[neighbour] == 0) {
                        zeroInDegree.addLast(neighbour);
                    }
                }
            }
        }

        boolean flg = true;
        for (int degree : inDegree) {
            if (degree > 0) {
                flg = false;
            }
        }
        if (flg == false) return 0;
        return ans;
 */
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if (n == 0 && m == 0) break;

            int sz = n+1;
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            ArrayList<HashSet<Integer>> parentGraph = new ArrayList<>();
            for (int i=0; i < sz; i++) {
                graph.add(new ArrayList<Integer>());
                parentGraph.add(new HashSet<>());
            }
            ArrayList<Integer> result = new ArrayList<>();
            int[] inDegree = new int[sz];

            int u, v;
            for (int i=0; i < m; i++) {
                u = sc.nextInt();
                v = sc.nextInt();
                graph.get(u).add(v);
                inDegree[v] += 1;
                parentGraph.get(v).add(u);
            }

            boolean ans = topoSort(graph, inDegree, result);
            if (ans) {
                boolean multipleOrderFlg = false;
                u = result.get(0);
                for (int i=1; i < result.size(); i++) {
                    v = result.get(i);
                    if (!parentGraph.get(v).contains(u)) {
                        multipleOrderFlg = true;
                        break;
                    }
                    u = v;
                }
                if (multipleOrderFlg) {
                    System.out.println(2);
                } else {
                    System.out.println(1);
                }
            } else {
                System.out.println(0);
            }
        }
        return;
    }
}