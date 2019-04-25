/** https://www.hackerrank.com/challenges/qheap1/problem
 *  idea: just use data structure heap
 */

import java.util.Scanner;
import java.util.PriorityQueue;

class QHeap1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i < q; i++) {
            int command = sc.nextInt();
            if (command == 1) {
                int val = sc.nextInt();
                pq.add(val);
            } else if (command == 2) {
                int val = sc.nextInt();
                pq.remove(val);
            } else if (command == 3) {
                if (!pq.isEmpty()) {
                    System.out.println(pq.peek());
                }
            }
        }
    }
}
