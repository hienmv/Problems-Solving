/** https://www.hackerrank.com/challenges/candies/problem
 *  tag: #greedy #divide-and-conquer #binary-search
 */

import java.util.Scanner;

class Candies {
    public static void concatnate(int[] arr, int left, int right, int mid, int[] result) {
        if (arr[mid] < arr[mid+1]) {
            if (result[mid] >= result[mid+1]) {
                int prev = 0;
                int interval = result[mid] - result[mid+1] + 1;
                for (int i=mid+1; i <= right; i++) {
                    if (arr[i] > prev) {
                        result[i] += interval;
                        prev = arr[i];
                    } else {
                        break;
                    }
                }
            }
        } else 
        if (arr[mid] > arr[mid+1]) {
            if (result[mid] <= result[mid+1]) {
                int prev = 0;
                int interval = result[mid+1] - result[mid] + 1;
                for (int i=mid; i >= left; i--) {
                    if (arr[i] > prev) {
                        result[i] += interval;
                        prev = arr[i];
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static void calculate(int[] arr, int left, int right, int[] result) {
        if (left == right) {
            result[left] = 1;
            return;
        }

        int mid = (right + left) / 2;
        calculate(arr, left, mid, result);
        calculate(arr, mid + 1, right, result);
        concatnate(arr, left, right, mid, result);
    }
    public static void main(String[] args) {
        // input
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i=0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // calculate
        int[] result = new int[n];
        calculate(arr, 0, n - 1, result);

        // result
        long ans = 0;
        for (int r : result) {
            ans += r;
        }
        System.out.println(ans);
    }
}


/**
 *
 * Greedy: run two time: left -> right  and right -> left
 * 
org:    1 2 3 4 4 1 2
L1:     1 2 3 4 1 1 2   (left -> right)
L2:     1 2 3 4 2(1+1) 1 2   (base on L1, right -> left)
*/
class CandiesGreedy {
     static long candies(int n, int[] arr) {
        // calculate
        int[] result = new int[n+1];
        result[0] = 1;
        for (int i=1; i < arr.length; i++) {
            if (arr[i] > arr[i-1]) {
                result[i] = result[i-1] + 1;
            } else {
                result[i] = 1;
            }
        }

        for (int i=arr.length - 2; i >= 0; i--) {
            if (arr[i] < arr[i+1]) {
                if (result[i] >= result[i+1]) {
                    result[i+1] += result[i] - result[i+1] + 1;
                }
            } else if (arr[i] > arr[i+1]) {
                if (result[i] <= result[i+1]) {
                    result[i] += result[i+1] - result[i] + 1;
                }
            }
        }

        // result
        long ans = 0;
        for (int r : result) {
            ans += r;
        }
        return ans;
    }
}