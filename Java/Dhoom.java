/** https://www.hackerearth.com/ja/practice/algorithms/graphs/breadth-first-search/practice-problems/algorithm/dhoom-4/description/
 *  #bfs
 *  comment: own key * other key -> a new key to check
 */

import java.util.Deque;
import java.util.Scanner;
import java.util.LinkedList;

public class Dhoom {

    static int check(int ownKey, int lockKey, int[] arr) {
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(ownKey);
        int countArr[] = new int[100001];
        for (int i = 0; i < countArr.length; i++) {
            countArr[i] = -1;
        }
        countArr[ownKey] = 0;
        while (!queue.isEmpty()) {
            if (countArr[lockKey] != -1) {
                break;
            }
            int v = queue.pollFirst();
            for (int i = 0; i < arr.length; i++) {
                int val = (int)(((long)v * arr[i]) % 100000);
                if (countArr[val] == -1) {
                    queue.addLast(val);
                    countArr[val] = countArr[v] + 1;
                }
            }
        }

        return countArr[lockKey];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ownKey = sc.nextInt();
        int lockKey = sc.nextInt();
        int n = sc.nextInt();
        int[] orgArr = new int[n];
        for (int i=0; i < n; i++) {
            int temp = sc.nextInt();
            orgArr[i] = temp;
        }
        System.out.println(check(ownKey, lockKey, orgArr));
    }
}
