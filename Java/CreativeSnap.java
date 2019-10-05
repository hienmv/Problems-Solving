/** https://codeforces.com/problemset/problem/1111/C
 * #divide-and-conquer #binary-search
 */

import java.util.Arrays;
import java.util.Scanner;

public class CreativeSnap {
    static int A, B;
    public static int getLowerBound(int[] arr, int val) {
        int ret = arr.length;
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= val) {
                ret = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return ret;
    }

    public static int getNumberAvenger(int[] arr, int left, int right) {
        // lower bound left and lower bound right
        int rightKey = getLowerBound(arr, right + 1);
        int leftKey = getLowerBound(arr, left);

        return rightKey - leftKey;
    }

    public static long calculate(int[] arr, int left, int right) {
        // base case
        int numberAvenger = getNumberAvenger(arr, left, right);
        if (numberAvenger == 0) {
            return A;
        }
        long normalResult = (long)B * numberAvenger * (right - left + 1);
        if (left == right) {
            return normalResult;
        }

        // divide
        int mid = (left + right) / 2;
        long result = 0;
        result += calculate(arr, left, mid);
        result += calculate(arr, mid+1, right);
        return Math.min(result, normalResult);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        A = sc.nextInt();
        B = sc.nextInt();
        int sz = (1 << n);
        int[] arr = new int[k];
        for(int i=0; i < k; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        
        long result = calculate(arr, 1, sz);
        System.out.println(result);
    }
}