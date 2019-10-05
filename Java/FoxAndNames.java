/** https://codeforces.com/problemset/problem/510/C
 * #topological-sort #graph #dfs-and-similar
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

class FoxAndNames {
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

    // return -1 if name 2 is prefix of name1, 100(max size of a string) if name1 is prefix of name2, or index at which name1 differ name2.
    static int getDiffIndex(String name1, String name2) {
        int ret = -1;
        int idx = 0;
        
        if (name1.contains(name2)) {
            return ret;
        }
        
        while (idx < name1.length() && idx < name2.length()) {
            if (name1.charAt(idx) != name2.charAt(idx)) {
                ret = idx;
                break;
            }
            idx++;
        }

        if (ret == -1 && name1.length() <= name2.length()) {
            ret = 100;
        }

        return ret;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sz = 'z' - 'a' + 1;

        // special case
        if ( n == 1) {
            for (int i='a'; i < sz; i++) {
                System.out.print(String.format("%c", i));
            }
            return;
        }

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i < sz; i++) {
            graph.add(new ArrayList<>());
        }
        ArrayList<Integer> resultArr = new ArrayList<>();
        int[] inDegree = new int[sz];
       
        
        int diffIdx;
        int u, v;
        String name1 = sc.next();
        String name2;
        for (int i=1; i < n; i++) {
            name2 = sc.next();
            diffIdx = getDiffIndex(name1, name2);
            if (diffIdx == -1) {
                System.out.println("Impossible");
                return;
            } else if (diffIdx == 100) {
                continue;
            }

            u = name1.charAt(diffIdx) - 'a';
            v = name2.charAt(diffIdx) - 'a';
            name1 = name2;
            graph.get(u).add(v);
            inDegree[v] += 1;
        }

        boolean isPossible = topoSorting(graph, inDegree, resultArr);
        if (isPossible) {
            for (int i=0; i < resultArr.size(); i++) {
                System.out.print(String.format("%c", resultArr.get(i) + 'a'));
            }
            System.out.println();
        } else {
            System.out.println("Impossible");
        }

        return;
    }
}