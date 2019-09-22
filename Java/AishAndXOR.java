/** 
 * idea: bit mapulation
 */

import java.util.Scanner;

class AishAndXOR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sz = n+1;
        int[] xorArr = new int[sz];
        int[] zeroArr = new int[sz];

        int val;
        for (int i=1; i < sz; i++) {
            val = sc.nextInt();
            xorArr[i] = xorArr[i-1] ^ val;
            zeroArr[i] = zeroArr[i-1] + ((val == 0) ? 1 : 0);
        }
       
        int q = sc.nextInt();
        int l, r, xorVal, zeroNum;
        for  (int i=0; i < q; i++) {
            l = sc.nextInt();
            r = sc.nextInt();
            xorVal = xorArr[r] ^ xorArr[l-1];
            zeroNum = zeroArr[r] - zeroArr[l-1];
            System.out.println(xorVal + " " + zeroNum);
        }
        return;
    }
}