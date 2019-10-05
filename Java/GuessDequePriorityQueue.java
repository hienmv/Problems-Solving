/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3146
 * #queue #stack #priorityqueue
 * provided that the data structure is used is queue/stack/priorityQueue(maxHeap). 
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.LinkedList;

class GuessDequePriorityQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            Deque<Integer> stack = new LinkedList<>();
            Deque<Integer> queue = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            boolean stackFlg = true;
            boolean queueFlg = true;
            boolean maxHeapFlg = true;
            for (int i=0; i < n; i++) {
                int command = sc.nextInt();
                int val = sc.nextInt();

                if(command == 1) {
                    if(stackFlg) {
                        stack.addLast(val);
                    }
                    if (queueFlg) {
                        queue.addLast(val);
                    }
                    if(maxHeapFlg) {
                        pq.add(val);
                    }
                } else {
                    if (stackFlg && !stack.isEmpty() && stack.peekLast() == val) {
                        stack.pollLast();
                    } else {
                        stackFlg = false;
                    }
                    
                    if (queueFlg && !queue.isEmpty() && queue.peekFirst() == val) {
                        queue.pollFirst();
                    } else {
                        queueFlg = false;
                    }
                    
                    if(maxHeapFlg && !pq.isEmpty() && pq.peek() == val) {
                        pq.poll();
                    } else {
                        maxHeapFlg = false;
                    }
                }
            }
            ArrayList<String> result = new ArrayList<>();
            if (queueFlg) {
                result.add("queue");
            }
            if (stackFlg) {
                result.add("stack");
            }
            if (maxHeapFlg) {
                result.add("priority queue");
            }
            if (result.size() == 0) {
                System.out.println("impossible");
            } else if (result.size() == 1) {
                System.out.println(result.get(0));
            } else {
                System.out.println("not sure");
            }
        }
    }
}