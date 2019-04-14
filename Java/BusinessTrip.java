/* https://codeforces.com/contest/149/problem/A
 * idea: short the array in the descending order
 * sum of any element that greater or equal k
 * */

import java.util.Scanner;
import java.util.Arrays;

public class BusinessTrip {
    
    static int calMinMonthNeeded() {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int[] arr = new int[12];
        for (int i=0; i < 12; i++) {
            int temp = sc.nextInt();
            arr[i] = temp;
        }
        Arrays.sort(arr);
        // special case
        if (k == 0) return 0;

        int count = 0;
        int sum = 0;
        for (int i=11; i >= 0; i--) {
            sum += arr[i];
            count++;
            if (sum >= k) {
                break;
            }
        }
        if (sum < k) {
            count = -1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(calMinMonthNeeded());
    }
}
