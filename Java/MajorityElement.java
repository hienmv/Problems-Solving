// https://leetcode.com/problems/majority-element/
// #array #divide-and-conquer #bit-manipulation
class Solution {
    // O(n) - O(n)
    /*
    public int majorityElement(int[] nums) {
        // element - appearance times
        HashMap<Integer, Integer> map = new HashMap<>();
        int minTimes = (nums.length + 1) / 2;
        
        int ret = -1;
        for (int e : nums) {
            if(map.containsKey(e)) {
                int newVal = map.get(e) + 1;
                if (newVal >= minTimes) return e;
                map.replace(e, newVal);
            }
            else {
                map.put(e, 1);
            }
        }
        return nums[0];
    }
    */
    
    // O(n) - O(1) Boyer - Moor Majority Alogorithms
    public int majorityElement(int[] nums) {
        int majority = getMajority(nums);
        if (!isRealMajority(nums, majority)) {
            return -1;
        }
        return majority;
    }
    private boolean isRealMajority(int[] nums, int majority) {
        int cnt = 0;
        for (int e : nums) {
            if (e == majority) {
                cnt++;
            }
        }
        if (cnt < (nums.length + 1) / 2) {
            return false;
        }
        return true;
    }
    private int getMajority(int[] nums) {
        int cnt = 1;
        int idxMajor = 0;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[idxMajor]) {
                cnt++;
            }
            else {
                cnt--;
            }
            if (cnt == 0) {
                cnt = 1;
                idxMajor = i;
            }
        }
            
        return nums[idxMajor];
    }
}
