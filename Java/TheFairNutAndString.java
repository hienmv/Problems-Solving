/**
 * https://codeforces.com/problemset/problem/1084/C
 * #dynamic-programming
 * chien thuat:
 *      1. liet ke cac cach 
 *      2. sap xep lai cac cach de tim cthuc (ket thuc tai cung vi tri...)
 *          (cung la de kiem chung lai xem cach hieu co dung k)
 
 p: position

s[p_i] = ki tu tai vi tri p_i

p_i 'b' p_i1

abbaa

result = 0;
pre_b = 0;
                    *     *     *
                    *       *   *
                    a b b a a b b a
result              1 1 1 3 5 5 5 1+5+5=11
pre_b               0 1 2 2 2 5 5
=> 3 + 2 = 5. 

a b b a a b b a
*
      *
*     *
        *
*       *
              *
*             *
      *       *
        *     *
*     *       *
*       *     *



 */

import java.util.Scanner;
public class TheFairNutAndString {
    private static int MOD = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "*" + sc.next(); // a trick
        int n = s.length();
        int[] result = new int[n];
        int preB = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == 'a') {
                result[i] = (result[i-1] + 1) % MOD;
                if (preB > 0) {
                    result[i] = (result[i] + result[preB - 1]) % MOD;
                }
            }
            else {
                if (s.charAt(i) == 'b') {
                    preB = i;
                }
                result[i] = result[i-1];
            }
        }
        System.out.println(result[n-1]);
    }
}