/**
 * http://codeforces.com/problemset/problem/1294/F
 #todo
 Duong kinh cua Tree la duong di dai nhat cua 2 dinh bat ky (leaf node)
 BFS S -> ...
dist[S] = 0
queue = [S]

multiple source DFS/BFS
dist[S[i]] = 0
queue = S


DFS/BFS 2 lan => tim ra dc duong kinh cua Tree:

    A
    |
    |
    |
    X---------C
    |
    |
    |
    |
    B

XB >= XC
XC > XA
XC < XA

Tim ra duong kinh cua tree.
    tim ra [multiple source] toi nhung diem khong thuoc duong kinh cua cay. 
        => ...
        result: duong kinh + ...

Mindset DP
            A
            |
            |
            |
            u---------C
            |
            v
            |
            |
            B

DP(u, longest)
 - X in child of u
 - X is u
  + A, B, C are children of u
  + B, C are childrend of u

 */
import java.util.Scanner;
import java.util.ArrayList;
public class ThreePathsOnTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int u, v;
        // input
        for (int i = 0; i <= n; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // leaf list
        ArrayList<Integer> leafList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (graph.get(i).size() == 1) {
                leafList.add(i);
            }
        }
        int result = 0;
        for (int leaf : leafList) {
            boolean[] visited = new boolean[n+1];
            visited[leaf] = true;
            result = Math.max(result, countTwoLongestRoute(graph, leaf, visited));
        }
        System.out.println(result);
    }
    
    private static int countTwoLongestRoute(ArrayList<ArrayList<Integer>> graph, int startNode, boolean[] visited) {
        while ()
    }
}