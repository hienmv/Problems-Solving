/**
#dp #lis

tim day chung (rieng biet) cua 2 day hoan vi/ hoac trong cung 1 tap
-> lis
 */

import java.util.Scanner;
import java.util.ArrayList;

class PrinceAndPrincess {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for(int t=1; t <= testcase; t++) {
            int n = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();
            int[] rank = new int[n*n + 1];
            int tmp;
            for (int i=0; i < p+1; i++) {
                tmp = sc.nextInt();
                rank[tmp] = i;
            }
            int[] arr = new int[q+1];
            for (int i=0; i < q+1; i++) {
                arr[i] = sc.nextInt();
                // simple: arr[i] = rank[sc.nextInt()];
            }
            int result = LIS(rank, arr);
            System.out.println("Case " + t + ": " + result);
        }
    }
    public static int LIS(int[] rank, int[] arr) {
        int len = 1;
        ArrayList<Integer> result = new ArrayList<>();
        result.add(arr[0]);
        for (int i=1; i < arr.length; i++) {
            int idx = arr[i];
            if (rank[idx] <= rank[result.get(0)]) {
                result.set(0, idx);
            }
            else if (rank[idx] > rank[result.get(len - 1)]) {
                result.add(idx);
                len++;
            }
            else {
                int pos = lowerBound(rank, result, len, rank[idx]);
                result.set(pos, idx);
            }
        }
        return result.size();
    }
    public static int lowerBound(int[] rank, ArrayList<Integer> result, int n, int v) {
        int left = 0, right = n;
        int pos = n; 
        while (left < right) { 
            int mid = left + (right - left) / 2;
            int idx = result.get(mid);
            if(rank[idx] > v) {
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