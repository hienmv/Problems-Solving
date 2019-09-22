/**
 * idea: TopoList
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashMap;

class Beverages {
    public static boolean topoSorting(ArrayList<ArrayList<Integer>> graph, int[] inDegree, ArrayList<Integer> resultArr) {
        PriorityQueue<Integer> zeroInDegree = new PriorityQueue<>();
        int sz = graph.size();
        boolean[] visitedArr = new boolean[sz];

        for (int i=0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                zeroInDegree.add(i);
            }
        }

        while(!zeroInDegree.isEmpty()) {
            int node = zeroInDegree.poll();
            resultArr.add(node);
            visitedArr[node] = true;
            for (int neighbour : graph.get(node)) {
                if (!visitedArr[neighbour]) {
                    inDegree[neighbour] -= 1;
                    if (inDegree[neighbour] == 0) {
                        zeroInDegree.add(neighbour);
                    }
                }
            }
        }

        boolean ret = true;
        for(int degree: inDegree) {
            if (degree > 0) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = 1;
        while(sc.hasNext()) {
            int n = sc.nextInt();
            if (testcase > 1) {
                System.out.println();
            }
            ArrayList<String> arrList = new ArrayList<>();
            HashMap<String, Integer> hashMap = new HashMap<>();

            String beverage;
            for (int i=0; i < n; i++) {
                beverage = sc.next();
                arrList.add(beverage);
                hashMap.put(beverage, arrList.size() - 1);

            }
            
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i=0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            ArrayList<Integer> resultArr = new ArrayList<>();
            int[] inDegree = new int[n];
        
            int m = sc.nextInt();
            String be1, be2;
            int idx1, idx2;
            for (int i=0; i < m; i++) {
                be1 = sc.next();
                be2 = sc.next();
                idx1 = hashMap.get(be1);
                idx2 = hashMap.get(be2);
                graph.get(idx1).add(idx2);
                inDegree[idx2] += 1;
            }
            topoSorting(graph, inDegree, resultArr);
            System.out.print("Case #" + testcase + ": Dilbert should drink beverages in this order: ");
            for(int j=0; j < resultArr.size(); j++) {
                System.out.print(arrList.get(resultArr.get(j)));
                if (j < resultArr.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println(".");
            testcase++;
        }
        return;
    }
}