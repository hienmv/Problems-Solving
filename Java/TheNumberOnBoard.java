/** https://codeforces.com/problemset/problem/835/B
 * #greedy
 */

import java.util.Scanner;
import java.util.Arrays;

class TheNumberOnBoard {
    public static void main(String[] args) {
        // input
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        String n = sc.next();
        char[] charArr = n.toCharArray();
        
        // calculate
        int sumDigits = 0;
        for (char c : charArr) {
            sumDigits += c - '0'; 
        }
        if (sumDigits >= k) {
            System.out.println(0);
            return;
        }
        // iterate from the minimum digit
        Arrays.sort(charArr);
        int ret = 0;
        for (char c : charArr) {
            sumDigits += '9' - c;
            ret++;
            if (sumDigits >= k) {
                break;
            }
        }
        System.out.println(ret);
    }
}