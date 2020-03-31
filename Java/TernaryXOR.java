/** https://codeforces.com/contest/1328/problem/C
 * #implementation
 */

import java.util.Scanner;
public class TernaryXOR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        for (int t = 0; t < testcases; t++) {
            int n = sc.nextInt();
            char[] s  = sc.next().toCharArray();
            char[] a = new char[n];
            char[] b = new char[n];
            a[0] = b[0] = '1';
            // provided that A <= B.
            boolean aLessThanB = false;
            for (int i = 1; i < n; i++) {
                if (s[i] == '2') {
                    if (aLessThanB) {
                        a[i] = '2';
                        b[i] = '0';
                    } 
                    else {
                        a[i] = '1';
                        b[i] = '1';
                    }
                }
                else if (s[i] == '1') {
                    if (!aLessThanB) {
                        aLessThanB = true;
                        a[i] = '0';
                        b[i] = '1';
                    }
                    else {
                        a[i] = '1';
                        b[i] = '0';
                    }
                }
                else {
                    a[i] = b[i] = '0';
                }
            }
            /* better implementation
            for (int i = 0; i < n; i++) {
                if (s[i] == '1') {
                    // a Less Than b
                    a[i] = '0';
                    b[i] = '1';
                    for (int j = i + 1; j < n; j++) 
                        a[j] = s[j];
                        b[j] = '0';
                    }
                    break;
                }
                else {
                    a[i] = b[i] = '0' + (s[i] - '0') / 2;
                }
            }
            */

            // result
            for (int i = 0; i < n; i++){
                System.out.print(a[i]);
            }
            System.out.println();
            for (int i = 0; i < n; i++){
                System.out.print(b[i]);
            }
            System.out.println();
        }
    }
}