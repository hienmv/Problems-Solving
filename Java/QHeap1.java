/** https://www.hackerrank.com/challenges/qheap1/problem
 *  idea: just use data structure heap
 *  https://www.interviewsansar.com/2015/05/16/what-is-time-complexity-for-offer-poll-and-peek-methods-in-priority-queue
 */

import java.util.Scanner;
import java.util.PriorityQueue;

public class QHeap1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> removed_pq = new PriorityQueue<>();
        for (int i=0; i < q; i++) {
            int command = sc.nextInt();
            if (command == 1) {
                int val = sc.nextInt();
                pq.add(val);
            } else if (command == 2) {
                int val = sc.nextInt();
                if (pq.contains(val)) {
                    removed_pq.add(val); // O(logN) -> O(NlogN)
                }
            } else if (command == 3) {

                while (!removed_pq.isEmpty() && !pq.isEmpty() 
                    && removed_pq.peek().equals(pq.peek())) 
                {
                    pq.poll(); // O(logN) 
                    removed_pq.poll(); // O(logN)
                }
                if (!pq.isEmpty()) {
                    System.out.println(pq.peek());
                }
            }
        }
    }
}