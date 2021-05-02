/**
 * http://codeforces.com/problemset/problem/1037/C
 * #dynamic-programming #greedy
 * 2 lien ke => nen swap (vi cost flip = 2, cost swap = 1)
 */
import java.util.Scanner;
public class Equalize {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String a = sc.next();
        String b = sc.next();

        int preChangePos = -2;
        int cost = 0;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                // swap
                if (preChangePos == i - 1 && a.charAt(i) != a.charAt(i-1)) {
                    // reset
                    preChangePos = -2;
                }
                else { // flip
                    cost++;
                    // update previous change position
                    preChangePos = i;
                }
            }
        }
        /** clear way
        int i = 0;
        while (i < n) {
            if (a.charAt(i) != b.charAt(i)) {
                if (i+1 < n && a.charAt(i+1) != b.charAt(i+1) && a.charAt(i+1) != a.charAt(i)) {
                    // swap
                    cost++;
                    i += 2;
                }
                else {
                    // flip
                    cost++;
                    i++;
                }
            }
            else {
                i++;
            }
        }
        
         */
        System.out.println(cost);
    }
}