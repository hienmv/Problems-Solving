/** http://codeforces.com/problemset/problem/161/D
 *  #dp on Tree. #divide-and-conquer #dfs
 */
/*
5 2
1 2
2 3
3 4
2 5
    k 

        4   3   5   2
arr[0]  1   1   1   1
arr[1]  0   1   0   2


arr[i] so dinh con cua vertex, di toi vertex ton i buoc
              1
           /    \
      2           6
    /   \  \
   3     5  7
   |
   4

  - duong di khong di qua dinh vertex
  - duong di di qua dinh vertex
    - chi di tu vertex xuong con:
      sum arr[k - 1] for each child
    - di qua 2 nhanh con cua vertex

gia su X dinh di toi 1 la a
       Y dinh di toi 1 la k - a
*/

import java.util.Scanner;
import java.util.ArrayList;

class DistanceInTree {

    public static void main(String[] args) {
        // input 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int sz = n+1;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i < sz; i++) {
            graph.add(new ArrayList<Integer>());
        }
        int u, v;
        for(int i=0; i < n-1; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // calculate
        // countArr[i] : count number of vertexs that distance between each vertext and current vertext is i 
        int[] countArr = new int[k];
        System.out.println(calculate(graph, 1, -1, k, countArr));
    }

    public static int calculate(ArrayList<ArrayList<Integer>> graph, int current, int parent, int k, int[] countArr) {
        // count number of vertexs that distance between vertex and current vertex is 0; (itself)
        countArr[0] = 1;
        
        int ans = 0; // base case

        for (int neighbour : graph.get(current)) {
            if (neighbour != parent) {
                int[] childCountArr = new int[k];
                // devide
                // count the directions that in one side of current vertex
                ans += calculate(graph, neighbour, current, k, childCountArr);
                // i, j: i + j + 1 = k -> j = k - i - 1
                // conquer result
                for (int i=0; i < k; i++) {
                    ans += countArr[i] * childCountArr[k - i - 1];
                }
                // merge sub-problem counter
                for (int i=0; i < k-1; i++) {
                    countArr[i+1] += childCountArr[i]; 
                }
            }
        }
        return ans;
    }
}