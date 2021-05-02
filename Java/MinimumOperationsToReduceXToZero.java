// https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
// #two-pointer #binary-search #sliding-window #greedy
class Solution {
    public int minOperations(int[] nums, int x) {
        // count total from left and put to hashmap, if total > x, stop; 
        // run from right, calculate current total, and find y that current total + y = x in hashmap

        // no need to check
        if (nums[0] == x) return 1;
        if (nums[nums.length - 1] == x) return 1;
        
        int total = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (total + nums[i] <= x) {
                total += nums[i];
                map.put(total, i+1);
            } else {
                break;
            }
        }
        
        
        int result = 100001;
        int current_total = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            current_total += nums[i];
            int count = nums.length - i;
            if (current_total == x) {
                if (count < result) {
                    result = count;
                    break;
                }
            } else if (current_total < x) {
                int gap = x - current_total;
                if (map.containsKey(gap)) {
                    count += map.get(gap);
                    if (count < result) {
                        result = count;
                    }
                }
            } else {
                if (map.containsKey(x)) {
                    count = map.get(x);
                    if (count < result) {
                        result = count;
                    }
                }
                break;
            }
        }
            
        
        return (result < 100001) ? result : -1;
    }
}