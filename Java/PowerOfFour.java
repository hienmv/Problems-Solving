// https://leetcode.com/problems/power-of-four/
// #bit-manipulation
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n < 0) return false;
        boolean isPowerOfTwo = (n & (n - 1)) == 0;
        return isPowerOfTwo && (n % 3 == 1);
    }
}