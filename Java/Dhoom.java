/** https://www.hackerearth.com/ja/practice/algorithms/graphs/breadth-first-search/practice-problems/algorithm/dhoom-4/description/
 *  idea: use BFS
 */
import java.util.Deque;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class Dhoom {

    static int check(int ownKey, int lockKey, int[] arr) {
        Deque<Integer> queue = new LinkedList<>();
        for (int val : arr ){
            queue.addLast(val);
        }
        int count = 0;
        int minCount = Integer.MAX_VALUE;
        int val = ownKey;
        while (!queue.isEmpty()) {
            int v = queue.pollFirst();
            val *= v;
            val %= 100000;
            count++;
            if (val == lockKey) {
                if ( count < minCount) {
                    minCount = count;
                }
            } else {
                queue.addLast(val);
            }
        }
        
        

        return -1;
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

/**
 /** https://www.hackerearth.com/ja/practice/algorithms/graphs/breadth-first-search/practice-problems/algorithm/dhoom-4/description/
 *  idea: use BFS
 */

/*
 xx xxx xxx x x
 1 + 1 + 1 + 1 + 6
 1 + 1 + 1 + 2 + 5
 1 + 1 + 1 + 3 + 4
 1 + 1 + 2 + 2 + 4
 1 + 1 + 2 + 3 + 3
 1 + 2 + 2 + 2 + 3
 2 + 2 + 2 + 2 + 2

 nCk = n! / k! / (n - k)!
     = 10! / 5! / 5!
     = 6 * 7 * 2 * 3
tam giác pascal
*/
import java.util.Deque;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class Dhoom {

    static int check(int ownKey, int lockKey, int[] arr) {
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(ownKey);
        int count[] = new int[100000];
        for (int i = 0; i < 100000; i++)
            count[i] = -1;
        count[ownKey] = 0;
        while (!queue.isEmpty()) {
            int v = queue.pollFirst();
            for (int i = 0; i < arr.length; i++) {
                int val = (v * arr[i]) % 100000;
                if (count[val] == -1) {
                    queue.addLast(val);
                    count[val] = count[v] + 1;
                }
            }
        }

        return count[lockKey];
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
  

 */