/**
 * #dp #lis
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
class WavioSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            // caculate LIS
            int[] resultLeft = new int[n];
            Arrays.fill(resultLeft, 1);
            LIS(arr, resultLeft);
            
            // LIS with reverse Array
            int[] resultRight = new int[n];
            Arrays.fill(resultRight, 1);
            int[] reverseArr = new int[n];
            for(int i=0; i < n; i++) {
                reverseArr[i] = arr[n - 1 - i];
            }
            LIS(reverseArr, resultRight);

            // result
            int result = 0, cnt;
            for(int i=0; i < n; i++) {
                cnt = Math.min(resultLeft[i], resultRight[n - 1 - i]);
                if (result < 2 * cnt - 1) {
                    result = 2 * cnt - 1;
                } 
            }
            System.out.println(result);
        }
    }
    public static void LIS(int[] arr, int[] resultLeft) {
        int len = 1;
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        for(int i=1; i < arr.length; i++) {
            if (arr[i] <= arr[result.get(0)]) {
                result.set(0, i);
            }
            else if (arr[i] > arr[result.get(len - 1)]) {
                result.add(i);
                len++;
                resultLeft[i] = len;
            }
            else {
                int pos = lowerBound(arr, result, len, arr[i]);
                resultLeft[i] = pos;
                result.set(pos, i);
            }
        }
    }
    public static int lowerBound(int[] arr, ArrayList<Integer> result, int n, int v) {
        int pos = n;
        int left = 0, right = n, mid, idx;
        while (left < right) {
            mid = left + (right - left) / 2;
            idx = result.get(mid);
            if (arr[idx] >= v) {
                pos = mid;
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return pos;
    }
}