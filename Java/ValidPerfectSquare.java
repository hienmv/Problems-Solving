// https://leetcode.com/problems/valid-perfect-square/
// #math #binary-search
class Solution {
    /* O(log(n)) 
    - Notice: tran so.
    */ 
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        long l = 2, r = num;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (mid * mid == num) return true;
            if (mid * mid > num) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return false;
    }
    /* O(sqrt(n))
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int l = 2, r = num / 2;
        while (l <= r) {
            if (l * l == num) return true;
            l++;
            r = num / l;
        }
        return false;
    }
    */
}