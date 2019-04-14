/** https://codeforces.com/problemset/problem/1088/B
 * idea: sort the arrays
 * each loop (k times) update the minimum value and print it out.
 */

import java.util.Scanner;
import java.util.Arrays;

public class EhabAndSubstraction {

    static void printNumZeroElement() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i=0; i < n; i++) {
            int temp = sc.nextInt();
            arr[i] = temp;
        }
        Arrays.sort(arr);
        // special case 
        if (k == 1) {
            System.out.println(arr[0]);
            return;
        }
        int[] resultArr = new int[k];
        resultArr[0] = arr[0];
        int minVal = arr[0];
        int count = 1;
        for (int i=1; i < n; i++) {
            if (arr[i] - minVal > 0){
                resultArr[count] = arr[i] - minVal;
                minVal = arr[i];
                count++;
            }
            if (count == k) {
                break;
            }
        }
        // result
        for (int i: resultArr) {
            System.out.println(i);
        }

    }
    public static void main(String[] args) {
        printNumZeroElement();
    }
}