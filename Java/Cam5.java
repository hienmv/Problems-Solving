/** https://www.spoj.com/problems/CAM5/
 * tag: #bfs #dfs
 */

 import java.util.Scanner;
 import java.util.ArrayList;
 import java.util.Deque;
 import java.util.LinkedList;

 class Cam5 {

    static void BFS(int vertex, ArrayList<ArrayList<Integer>> listFriends, boolean[] countedArr) {
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(vertex);
        countedArr[vertex] = true;
        while(!queue.isEmpty()) {
            int p = queue.pollFirst();
            for (int k : listFriends.get(p)) {
                if (!countedArr[k]) {
                    countedArr[k] = true;
                    queue.addLast(k);
                }
            }
        }
    }
    static int cal(ArrayList<ArrayList<Integer>> listFriends) {
        boolean[] countedArr = new boolean[listFriends.size()];
        int count = 0;        
        for (int i=0; i < listFriends.size(); i++) {
            if (!countedArr[i]) {
                count++;
                BFS(i, listFriends, countedArr);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] resultArr = new int[t];
        for(int i=0; i < t; i++) {
            int people = sc.nextInt();
            int e = sc.nextInt();
            if (e == 0) {
                resultArr[i] = people;
                continue;
            }
            ArrayList<ArrayList<Integer>> listFriends = new ArrayList<>(people);
            for(int j=0; j < people; j++) {
                listFriends.add(new ArrayList<Integer>());
            }
            for(int j=0; j < e; j++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                listFriends.get(a).add(b);
                listFriends.get(b).add(a);
            }
            resultArr[i]= cal(listFriends);
        }

        for(int r : resultArr) {
            System.out.println(r);
        }

    }
 }