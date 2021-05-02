// https://leetcode.com/problems/count-number-of-nice-subarrays/
// #two-pointer
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        // slice window
        // use an array to store all index at which its element is odd
        // iterate this array to with slice window size = k and update result
        
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(-1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) {
                list.add(i);
            }
        }
        list.add(nums.length);
        int result = 0;
        int left = 1;
        for (int i = 1; i < list.size() - 1; i++) {
            if (i - left + 1 == k) {
                int count = (list.get(left) - list.get(left - 1)) * (list.get(i + 1) - list.get(i)); 
                result += count;
                left++;
            }
        }
        return result;
    }
}