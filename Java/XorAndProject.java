/** https://www.hackerearth.com/practice/basic-programming/bit-manipulation/basics-of-bit-manipulation/practice-problems/algorithm/chinu-and-his-project/description/
#bit-manipulation
*/

import java.util.Scanner;

class XorAndProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        // 32 bit
        int idx = 0;
        for (int i=31; i >= 0; i--) {
            if (((n >> i) & 1) == 1) {
                idx = i;
                break;
            }
        }
        int ans = 0;
        int v1, v2;
        for (int i=idx; i >=0; i--) {
            v1 = ((ans >> (i+1)) & 1); 
            v2 = (n >> i) & 1;
            if ((v1^v2) == 1) {
                ans |= (1 << i);
            } // default is 0
        }
        System.out.println(ans);
    }
}