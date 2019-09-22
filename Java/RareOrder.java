/**
 * https://icpcarchive.ecs.baylor.edu/index.php?option=onlinejudge&page=show_problem&problem=3140
 * idea: topoSort
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Arrays;

class RareOrder {

    public static boolean topoSorting(ArrayList<ArrayList<Integer>> graph, int[] inDegree, ArrayList<Integer> resultArr) {
        Deque<Integer> zeroInDegree = new LinkedList<>();
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

    // return -1 if word2 is prefix of word1, 20(max size of a word) if word1 is prefix of word2, or index at which word1 differ word2.
    static int getDiffIndex(String word1, String word2) {
        int ret = -1;
        int idx = 0;
        
        if (word1.contains(word2)) {
            return ret;
        }
        
        while (idx < word1.length() && idx < word2.length()) {
            if (word1.charAt(idx) != word2.charAt(idx)) {
                ret = idx;
                break;
            }
            idx++;
        }

        if (ret == -1 && word1.length() <= word2.length()) {
            ret = 20;
        }

        return ret;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sz = 'Z' - 'A' + 1;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i < sz; i++) {
            graph.add(new ArrayList<>());
        }
        ArrayList<Integer> resultArr = new ArrayList<>();
        int[] inDegree = new int[sz];
        Arrays.fill(inDegree, -1);
        
        int diffIdx;
        int u, v;
        String word1="", word2;
        while (true) {
            word2 = sc.next();
            if (word2.equals("#")) {
                break;
            }
            if (word1.isEmpty()) {
                word1 = word2;
                u = word1.charAt(0) - 'A';
                inDegree[u] = 0;
                continue;
            }
            
            diffIdx = getDiffIndex(word1, word2);
            if (diffIdx != 20 && diffIdx != -1) {
                u = word1.charAt(diffIdx) - 'A';
                v = word2.charAt(diffIdx) - 'A';
                graph.get(u).add(v);
                if (inDegree[v] < 0) {
                    inDegree[v] = 1;
                } else {
                    inDegree[v] += 1;
                }
            }
            word1 = word2;
        }

        topoSorting(graph, inDegree, resultArr);
        for (int i=0; i < resultArr.size(); i++) {
            System.out.print(String.format("%c", resultArr.get(i) + 'A'));
        }
        System.out.println();
        
        return;
    }
}