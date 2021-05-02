// https://leetcode.com/problems/reverse-bits/
// #bit-manipulation

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i=0; i < 32; i++)
        {
            res <<= 1;
            int end = n & 1;
            res = res | end;
            n >>= 1;
        }
        return res;
    }
//     // you need treat n as an unsigned value
//     public int reverseBits(int n) {
//         int mask = 1 << 31;
        
//         int res = 0;
//         for (int i = 0; i <= 31; i++) {
//             // System.out.println(Integer.toBinaryString(n));
//             if ((n & 1) > 0) {
//                 res |= mask;
//             }
//             n >>= 1;
//             mask >>= 1;
//             // System.out.println(Integer.toBinaryString(res));
//             System.out.println(Integer.toBinaryString(mask));
//             // System.out.println("nn");
//         }
//         return res;
//     }
}