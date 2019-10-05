/** https://codeforces.com/problemset/problem/1106/C
 * #greedy #implementation #math #sorting
 * the max element must be divided in a group with another element that you have to make sure the the square of group is minimum
 * => sort the array and make two pointer: from left, right => make efficient group
 */

import java.util.Scanner;
import java.util.Arrays;

 public class NumberDivision {
    static long calMinSum() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i=0; i < n; i++){
            int temp = sc.nextInt();
            arr[i] = temp;
        }
        Arrays.sort(arr);
        long result = 0;
        for (int i= 0, j= n-1; i <j; i++, j--){
            result += (long)(arr[i] + arr[j]) * (arr[i] + arr[j]);
        }

        return result;
    }
    public static void main(String[] args) {
        System.out.println(calMinSum());
    }
 }