/**  https://codeforces.com/problemset/problem/1011/A
 * #greedy #sorting #implementation
 *  two way: sort the character array.
 *  or use arr[26] to store characters.
*/

import java.util.Scanner;
import java.util.Arrays;

public class Stages {

    static int calMinimalWeigth() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        char[] arr = new char[n];
        String s = sc.next();
        arr = s.toCharArray();
        Arrays.sort(arr);

        int sum = (arr[0] - 'a' + 1);
        if (k == 1) {
            return sum;
        }
        int count = 1;
        int result = -1;
        int prevIdx = 0;
        for (int i=1; i < n; i++){
            if (arr[i] != arr[prevIdx] && arr[i] != (arr[prevIdx] + 1)) {
                count += 1;
                sum += (arr[i] - 'a' + 1);
                prevIdx = i;
            }
            if (count == k) {
                result = sum;
                break;
            }
        }

        return result;
    }
    public static void main(String[] args) {
        System.out.println(calMinimalWeigth());
    }
}