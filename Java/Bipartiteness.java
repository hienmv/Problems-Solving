/** https://codeforces.com/problemset/problem/862/B
 *  tag: #dfs #graph #tree
 *  dfs and similar: create sets of graph, 
 *  and check number of vertexes in set 2 that each vertex in set 1 can connect with.
 */

import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Bipartiteness {
    public static void createPartSet(ArrayList<ArrayList<Integer>> graph, HashSet[] partSet) {
        int startNode = 1;
        int sz = graph.size();
        boolean[] visitedArr = new boolean[sz];
        Deque<Integer> dq = new LinkedList<Integer>();
        dq.addLast(startNode);
        visitedArr[startNode] = true;
        partSet[0].add(startNode);

        int setIdx, node;

        while(!dq.isEmpty()) {
            node = dq.pollLast();
            if (partSet[0].contains(node)) {
                setIdx = 0;
            } else {
                setIdx = 1;
            }
            for (Integer neighbour : graph.get(node)) {
                if (!visitedArr[neighbour]) {
                    visitedArr[neighbour] = true;
                    partSet[1-setIdx].add(neighbour);
                    dq.add(neighbour);
                }
            }
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sz = n + 1;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
        for (int i=0; i < sz; i++) {
            graph.add(new ArrayList<Integer>());
        }

        int u,v;
        for (int i=0; i < n-1; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        // create bipartiteness 
        HashSet<Integer>[] partSet = new HashSet[2];
        partSet[0] = new HashSet<>();
        partSet[1] = new HashSet<>();
        createPartSet(graph, partSet);

        // count the number of edges can be added.
        int setIdx;
        if (partSet[0].size() <= partSet[1].size()){
            setIdx = 0;
        } else {
            setIdx = 1;
        }

        long ans = 0;
        for (int node : partSet[setIdx]) {
            ans += n - 1 - (partSet[setIdx].size() - 1) - graph.get(node).size();
        }
        System.out.println(ans);

    }
}