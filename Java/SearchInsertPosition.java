// https://leetcode.com/problems/search-insert-position/
// #array #binary-search
class Solution {
    public int searchInsert(int[] nums, int target) {
        int ret = -1;
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                ret = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return ret + 1;
    }
}