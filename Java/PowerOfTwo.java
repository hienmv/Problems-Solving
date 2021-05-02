// https://leetcode.com/problems/power-of-two/
// #math #bit-manipulation
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }
}