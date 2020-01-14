/**
 * #lis #dp
 * 
  12 18 0 4 2 1
  6
  18 12 4 2 1
  
  
  14 18 0 15 16 2 1
  14      15  16
                2 1
 *  LIS/LDS from end to start which ends at index i
 */
import java.util.Scanner;
import java.util.ArrayList;
class Trainsorting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for (int t = 0; t < testcase; t++) {
            int n = sc.nextInt();
            if (n == 0) {
                System.out.println(0);
                continue;
            }
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int result = 0;
            
            /*
            lis
                lis[i] = lis end at i
            
            */
            int[] lis = new int[n];
            int[] lds = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                // int lis = LIS(arr, i);
                // int lds = LDS(arr, i);
                // n*n
                lis[i] = lds[i] = 1;
                for (int j = i + 1; j < n; j++) {
                    if (arr[i] < arr[j])
                        lis[i] = max(lis[i], lis[j] + 1);
                    if (arr[i] > arr[j])
                        lds[i] = max(lds[i], lds[j] + 1);
                }
                
                result = Math.max(result, lis[i] + lds[i] - 1);
            }
            System.out.println(result);
        }
    }
    public static int LDS(int[] arr, int endIdx) {
        int len = 1;
        // int[] lds;
        ArrayList<Integer> result = new ArrayList<>();
        result.add(endIdx);
        for (int i = arr.length - 1; i > endIdx; i--) {
            if(arr[i] > arr[result.get(0)]) {
                result.set(0, i);
                // lds[i] = 1
            }
            else if (arr[i] < arr[result.get(len - 1)]) {
                result.add(i);
                len++;
                // lds[i] = len;
            }
            else {
                int pos = lowerBoundLDS(arr, result, len, arr[i]);
                result.set(pos, i);
                // lds[i] = pos + 1
            }
        }
        return len;
    }
    public static int lowerBoundLDS(int[] arr, ArrayList<Integer> sub, int len, int v) {
        int left = 0;
        int right = len;
        int pos = right;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int idx = sub.get(mid);
            if (arr[idx] < v) {
                pos = mid;
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return pos; 
    }
    public static int LIS(int[] arr, int endIdx) {
        int len = 1;
        ArrayList<Integer> result = new ArrayList<>();
        result.add(endIdx);
        for (int i = arr.length - 1; i > endIdx; i--) {
            if(arr[i] <= arr[result.get(0)]) {
                result.set(0, i);
            }
            else if (arr[i] > arr[result.get(len - 1)]) {
                result.add(i);
                len++;
            }
            else {
                int pos = lowerBoundLIS(arr, result, len, arr[i]);
                result.set(pos, i);
            }
        }
        return len;
    }
    public static int lowerBoundLIS(int[] arr, ArrayList<Integer> sub, int n, int v) {
        int left = 0;
        int right = n;
        int pos = right;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int idx = sub.get(mid);
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