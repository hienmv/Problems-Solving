/* https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=382
#backtracking

backtracking(state):
    // success
    row == N
    start = end
    
    // failed
    // if !valid -> false
    
    
    // try:
    for next_state (for each option)
        if valid
            set next_state
            call backtracking(next_state)
            reverse state
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

class Lotto {
    public static int subSetLen = 6;

    public static void backTracking(int[] arr, int[] result, int curIdx, int startIdx, int len) {
        
        // sucess
        if (len == 0)
        {
            for (int i=0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }
        // failed
        if (startIdx == arr.length) {
            return;
        }

        for (int i = startIdx; i < arr.length; i++) {
            // try
            len -= 1;
            result[curIdx] = arr[i];
            backTracking(arr, result, curIdx + 1, i + 1, len);
            len += 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k;
        while (true) {
            k = sc.nextInt();
            if (k == 0) break;

            int[] arr = new int[k];
            for (int i=0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }
            int[] result = new int[subSetLen];
            backTracking(arr, result, 0, 0, subSetLen);
            System.out.println();
        }

        return;
    }
}