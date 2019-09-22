/** 
 * idea: XOR
 * mindset / method DP, .. 

 a[0] + a[1] + a[2] + (a[0] + a[1]) + (a[1] + a[2]) + (a[0] + a[1] + a[2])
 -> a[0] + a[0] + a[0] + a[1] + a[1] + a[1] + a[1] + a[2] + a[2] + a[2]

 find number of array that contains that elements.

Explain:
 i-th
 
 N
 
 i-1    i    n-i

 (i)    *    (n + 1 - i)  -> number of array containing i-th
*/

import java.util.Scanner;

class SansaAndXOR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        for (int t=0; t < testcases; t++) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            int num;
            for (int i=0; i < n; i++) {
                num = sc.nextInt();
                arr[i] = num;
            }
            
            if (n % 2 != 0) {
                int val = 0;
                for (int i=0; i < n; i++) {
                    if (i % 2 == 0) {
                        val ^= arr[i];
                    }
                }
                System.out.println(val);
            } else {
                System.out.println(0);
            }
        }
    }
}
