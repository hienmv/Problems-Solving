// https://leetcode.com/problems/sort-colors/
// #array #two-pointer #sorting
class Solution {
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public void sortColors(int[] nums) {
        int idx0 = 0;
        int idx2 = nums.length - 1;
        int i = 0;
        while(i <= idx2) {
            // move to tail of array
            if(nums[i] == 2) {
                swap(nums, i, idx2--);
            }
            else {
                // move to head of array
                if(nums[i] == 0) {
                    swap(nums, i, idx0++);
                }
                i++;
            }
        }
    }
}