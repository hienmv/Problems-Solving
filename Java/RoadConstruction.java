/** http://lightoj.com/volume_showproblem.php?problem=1041
 *  #prim 
 */

import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.PriorityQueue;

class RoadConstruction {
    public static boolean Prim(ArrayList<ArrayList<Node>> graph, int[] costArr, int src) {
        int sz = graph.size();
        boolean[] visitedArr = new boolean[sz];
        Arrays.fill(visitedArr, false);
        
        costArr[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, 0));
        int id, w;
        Node node;
        while(!pq.isEmpty()) {
            node = pq.poll();
            id = node.id;
            visitedArr[id] = true;
            for (Node neighbour : graph.get(id)) {
                w = neighbour.cost;
                if (!visitedArr[neighbour.id] && w < costArr[neighbour.id]) {
                    costArr[neighbour.id] = w;
                    pq.add(new Node(neighbour.id, w));
                }
            }
        }

        for (boolean flg : visitedArr) {
            if(!flg) return false;
        }

        return true;
    }

    public static int getCostRepair(int[] costArr) {
        int ans = 0;
        for (int cost: costArr) {
            ans += cost;
        }
        return ans;
    }

    public static int getIdxInput(String str, HashMap<String, Integer> hashMap, ArrayList<String> stringArr, ArrayList<ArrayList<Node>> graph) {
        if (!hashMap.containsKey(str)) {
            stringArr.add(str);
            int id = stringArr.size() - 1;
            hashMap.put(str, id);
            graph.add(new ArrayList<>());
        } 
        return hashMap.get(str);
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        for (int t=1; t <= testcases; t++) {
            int m = sc.nextInt();
            HashMap<String, Integer> hashMap = new HashMap<>();
            ArrayList<String> stringArr = new ArrayList<>();
            ArrayList<ArrayList<Node>> graph = new ArrayList<>();
            
            String str1, str2;
            int cost, id1, id2;
            for (int i=0; i < m; i++) {
                str1 = sc.next();
                str2 = sc.next();
                cost = sc.nextInt();
                id1 = getIdxInput(str1, hashMap, stringArr, graph);
                id2 = getIdxInput(str2, hashMap, stringArr, graph);
                graph.get(id1).add(new Node(id2, cost));
                graph.get(id2).add(new Node(id1, cost));
            }

            int sz = graph.size();
            int[] costArr = new int[sz];
            Arrays.fill(costArr, Integer.MAX_VALUE);
            if (Prim(graph, costArr, 0)) {
                System.out.println("Case " + t + ": " + getCostRepair(costArr));
            } else {
                System.out.println("Case " + t + ": Impossible");
            }
        }
    }
}

class Node implements Comparable<Node> {
    int id, cost;
    public Node(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}