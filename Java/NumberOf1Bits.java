// https://leetcode.com/problems/number-of-1-bits/
// #bit-manipulation
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // idea shift bit => count last bit
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int is_bit_one = n & 1;
            if (is_bit_one != 0) {
                res++;
            }
            n >>= 1;
        }
        return res;
    }
}