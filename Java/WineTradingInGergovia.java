/* https://www.spoj.com/problems/GERGOVIA/
#greedy

arr1  0 2 4
      0 0 0
      
arr2  1  3
      0  0
       
            0 1 2 3 4 5
      i         I
      j.      I
result = 4 * 1 + 1 * 3 + 1* 1 + 1 * 1
*/

import java.util.Scanner;
import java.util.ArrayList;

class WineTradingInGergovia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            int n = sc.nextInt();
            if (n == 0) break;

            // input
            int[] arr = new int[n];
            ArrayList<Integer> buyList = new ArrayList<>();
            ArrayList<Integer> sellList = new ArrayList<>();
            for (int i=0; i < n; i++) {
                arr[i] = sc.nextInt();
                if (arr[i] > 0) {
                    buyList.add(i);
                } else {
                    sellList.add(i);
                }
            }

            // calculate
            int i=0, j=0, sum = 0;
            int movedUnit = 0;
            long cost = 0;
            while (i < buyList.size() && j < sellList.size()) {
                sum = arr[buyList.get(i)] + arr[sellList.get(j)];
                movedUnit = Math.min(Math.abs(arr[buyList.get(i)]), Math.abs(arr[sellList.get(j)]));
                cost += (long)movedUnit * Math.abs(buyList.get(i) - sellList.get(j));
                
                if (sum == 0) {
                    i++;
                    j++;
                }
                else if (sum > 0) {
                    arr[buyList.get(i)] = sum;
                    j++;
                }
                else {
                    arr[sellList.get(j)] = sum;
                    i++;
                }
            }
            System.out.println(cost);
        }
    }
}

/**
better way:
calculate cost at each position:

a[i] > 0: move a[i] to right
a[i] < 0: receive -a[i] from right

-> abs(a[i])
s = (a[0] + a[1])
s = (a[0] + a[1] + a[2])
s > 0: move s to right
s < 0: receive -s from the right.

result = 0
s = 0

for (int i=0; i < n; i++) {
    result += abs(s)
    s += a[i]

}

 5         x
a:    5 -4 1 -3 1
r: 0  0  5 6  8 9
s: 0  5  1 2 -1 0

* NOTE: the difficuty of greedy method is that MUST to prove the "base case" true.
 */