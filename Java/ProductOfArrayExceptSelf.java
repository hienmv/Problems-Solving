// https://leetcode.com/problems/product-of-array-except-self/
// #array
class Solution {
    /*
    product prefix and suffix (first).
    */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];
        output[n-1] = 1;
        int pre = output[n-1];
        for (int i = n - 2; i >= 0; i--) {
            output[i] = nums[i+1] * pre;
            pre = output[i];
        }
        pre = 1;
        for (int i = 0; i < n ; i++) {
            output[i] *= pre;
            pre *= nums[i]; 
        }
        return output;
    }
}