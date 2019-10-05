/** https://codeforces.com/problemset/problem/644/B
 * #queue #*special-problem #constructive-algorithms #two-pointers
 *  use queue and calculate the finish time fo current process.
 */

import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

public class ProcessingQueries {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int b = sc.nextInt();
        Deque<Long> queue = new LinkedList<Long>();

        long[] result = new long[n];
        ArrayList<Long> finishTimeArr = new ArrayList<>();
        long curFinishTime = sc.nextInt();
        curFinishTime += sc.nextInt();
        finishTimeArr.add(curFinishTime);

        for (int i = 1; i < n; i++) {
            long receivedTime = sc.nextInt();
            long processTime = sc.nextInt();

            while (!queue.isEmpty() && curFinishTime < receivedTime) {
                curFinishTime += queue.pollFirst();
                finishTimeArr.add(curFinishTime);    
            } 

            if (receivedTime < curFinishTime) {
                if (queue.size() < b) {
                    queue.addLast(processTime);
                } else {
                    result[i] = -1;
                }
            } else {
                curFinishTime = receivedTime;
                queue.addLast(processTime);
            }
        }
        
        while (!queue.isEmpty()) {
            curFinishTime += queue.pollFirst();
            finishTimeArr.add(curFinishTime);    
        }
        int idx = 0;
        for (int i=0; i < n; i++) {
            if( result[i] == 0) {
                result[i] = finishTimeArr.get(idx);
                idx++;
            }
            System.out.print(result[i] + " ");
        }
        System.out.println("");
    }
}