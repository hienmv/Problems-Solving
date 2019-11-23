/** https://codeforces.com/problemset/problem/262/B
 * #greedy #sorting
 */

import java.util.Scanner;
import java.util.Arrays;

class RomaAndChangingSigns {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i=0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        // calculate
        for (int i=0; i <  n && k > 0; i++, k--) {
            if (arr[i] >= 0) {
                break;
            }
            arr[i] *= -1; 
        }

        if (k % 2 == 1) {
            Arrays.sort(arr);
            arr[0] *= -1;
        }

        // result
        int ret = 0;
        for (int a : arr) {
            ret += a;
        }
        System.out.println(ret);
    }

    // O(n)
    public static void betterSolution() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        for (int i=0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        
        // calculate
        int lastNeg = -1;
        for (int i=0; i < n && k > 0; i++, k--) {
            if (arr[i] >= 0) {
                break;
            }
            sum -= 2 * arr[i];
            lastNeg = i;
        }
        
        if (k % 2 == 1) {
            if (lastNeg == -1) {
                sum -= 2 * arr[0];
            }
            else if (lastNeg == n - 1) {
                sum += 2 * arr[n-1];
            }
            else {
                sum -= 2 * Math.min(Math.abs(arr[lastNeg]), Math.abs(arr[lastNeg+1]));
            }
        }

        System.out.println(sum);
    }
}