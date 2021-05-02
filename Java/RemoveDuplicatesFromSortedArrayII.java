// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
// #array #two-pointer
class Solution {
    public int removeDuplicates(int[] nums) {
        // idea: compare with 'previous' element. => 0(N)
        // next_index - index that next element should be placed in result array.
        // count - count the frequency of current element.
        int prev_element = nums[0];
        int next_index = 1;
        int count = 1;
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] == prev_element) {
                count++;
                // duplicate > 2 => ignore
                if (count > 2) {
                    
                } else {
                    nums[next_index] = nums[i];
                    next_index++;
                }
            } else {
                count = 1;
                nums[next_index] = nums[i];
                prev_element = nums[next_index];
                next_index++;
            }
        }
        
        return next_index;
    }
}