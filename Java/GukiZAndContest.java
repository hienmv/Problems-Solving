/*  https://codeforces.com/contest/551/problem/A
  #implementation #sorting #binary-search
 */

import java.util.Scanner;
import java.util.Arrays;

public class GukiZAndContest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] orgArr = new int[n];
        int[] sortedArr = new int[n];
        for (int i=0; i< n; i++) {
            int temp = sc.nextInt();
            orgArr[i] = temp;
            sortedArr[i] = temp;
        }
        Arrays.sort(sortedArr);
        
        for (int i=0; i < n; i++) {
            int idx = Arrays.binarySearch(sortedArr, orgArr[i]);
            int lastIdx = idx;
            while (idx >= 0) {
                lastIdx = idx;
                idx = Arrays.binarySearch(sortedArr, idx+1, n, orgArr[i]);
            }
            orgArr[i] = n - lastIdx;
        }
        for (int i: orgArr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
