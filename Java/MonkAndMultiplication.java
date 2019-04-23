/** https://www.hackerearth.com/practice/data-structures/trees/heapspriority-queues/practice-problems/algorithm/monk-and-multiplication/
 *  idea: each 3 elements will create to a heap.
0 1 2 3 4
1 6 5 4 3

      1
    /. `
  3.    5
/. `
4   6 

-1 -1  30 120  120

1 6 5
6 5 4
6 5 4
*/

import java.util.Scanner;
import java.util.PriorityQueue;

class MonkAndMultiplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int[] arr = new int[n];
        for (int i=0; i < n; i++) {
            int tmp = sc.nextInt();
            arr[i] = tmp;
        }
        long[] result = new long[n];
        // special case
        if(n < 3) {
            for (int i=0; i < n; i++) {
                System.out.println(-1);
            }
            return;
        } else {
            result[0] = -1;
            result[1] = -1;
        }
        
        result[2] = (long)arr[0] * arr[1] * arr[2];
        PriorityQueue<Integer> pq = new PriorityQueue<>(3);
        pq.add(arr[0]);
        pq.add(arr[1]);
        pq.add(arr[2]);
        long multiple = result[2];
        for (int i=3; i < n; i++) {
            if (arr[i] > pq.peek()){
                int tmp = pq.poll();
                multiple /= tmp;
                pq.add(arr[i]);
                multiple *= arr[i];
            }
            result[i] = multiple;

            // other way: add two element, poll 3, add 3.
        }

        for (long i : result) {
            System.out.println(i);
        }
    }
}