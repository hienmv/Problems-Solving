/** https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1415
 *  idea: use binary search to find the first idx
 */

import java.util.Scanner;
import java.util.Arrays;

class Marble {
    public static int bsFirst(int[] arr, int val) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right-left) / 2;
            if ((mid == left || arr[mid-1] < arr[mid]) && arr[mid] == val) {
                return mid;
            } else if (arr[mid] < val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, q, tmp, idx;
        int cases = 0;
        while (true) {
            cases++;
            n = sc.nextInt();
            q = sc.nextInt();
            if (n == 0 && q == 0) break;

            int[] arr = new int[n];
            for (int i=0; i < n; i++) {
                tmp = sc.nextInt();
                arr[i] = tmp;
            }
            Arrays.sort(arr);
            System.out.println("CASE# " + cases + ":");
            for (int i=0; i < q; i++) {
                tmp = sc.nextInt();
                idx = bsFirst(arr, tmp);
                if (idx == -1) {
                    System.out.println( tmp + " not found");
                } else {
                    System.out.println(tmp + " found at " + (idx+1));
                }
            }
        }
    }
}