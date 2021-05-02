/** https://codeforces.com/problemset/problem/500/B
 *  #dfs #dsu #greedy #math #sorting
 * 
 * 
 * -> vua xay, va xu ly tap hop
 * -> xay xong tap hop, roi moi xu ly.
 * -> b,c div 2.
 * 
 * 
1 2 3 ... <-
a[idx] ... a[i] < a[j] --- a[k] 


      a[idx]
      /     \
  a[i]      a[j]
                \
                a[k]




1 3 2 4 5



5 6 4 3 2 7 1
0001001
0000100
0000010
1000001
0100000
0010000
1001000

1 - 4
1 - 7
3 - 6
4 - 7
2 - 5

(1, 4, 7)
(5, 3, 1) -> (1, 3, 5)

(3, 6)
(4, 7)

(2, 5)
(6, 2) -> (2, 6)

init: 5 6 4 3 2 7 1
gr 1: 1 6 4 3 2 7 5
gr 2: 
gr 3: 1 2 4 3 6 7 5

4 2 1 5 3
00100
00011
10010
01101
01010

 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Deque;

public class NewYearPermutation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sz = n+1;
        int[] arr = new int[sz];
        int[] arrIdx = new int[sz];
        char[][] arrChar = new char[n][n];
        int tmp;
        for (int i=1; i < sz; i++) {
            tmp = sc.nextInt();
            arr[i] = tmp;
            arrIdx[tmp] = i;
        }
        String str;
        for (int i=0; i < n; i++) {
            str = sc.next();
            arrChar[i] = str.toCharArray();
        }
        
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(sz);
        for (int i=0; i < sz; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i=0; i < arrChar.length; i++) {
            for (int j=0; j < arrChar[0].length; j++) {
                if (arrChar[i][j] == '1') {
                    graph.get(i+1).add(j+1);
                    graph.get(j+1).add(i+1);
                }
            }
        }

        boolean[] visitedArr = new boolean[sz];
        Arrays.fill(visitedArr, false);
        for (int node=1; node < sz; node++) {
            if (!visitedArr[arrIdx[node]]) {
                DFS(graph, visitedArr, arrIdx[node], arr);
            }
        }

        for (int i=1; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void DFS(ArrayList<ArrayList<Integer>> graph, boolean[] visitedArr, int vertex, int[] arr) {
        ArrayList<Integer> idxList = new ArrayList<>();
        ArrayList<Integer> valList = new ArrayList<>();
        idxList.add(vertex);
        valList.add(arr[vertex]);

        Deque<Integer> queue = new LinkedList<>();
        visitedArr[vertex] = true;
        queue.addLast(vertex);
        while(!queue.isEmpty()) {
            int node = queue.pollLast();
            for (int neighbourNode : graph.get(node)) {
                if (!visitedArr[neighbourNode]) {
                    visitedArr[neighbourNode] = true;
                    queue.addLast(neighbourNode);
                    
                    idxList.add(neighbourNode);
                    valList.add(arr[neighbourNode]);
                }
            }
        }

        Collections.sort(valList);
        Collections.sort(idxList);

        for (int i = 0; i < idxList.size(); i++) {
            arr[idxList.get(i)] = valList.get(i); 
        }
    }
}