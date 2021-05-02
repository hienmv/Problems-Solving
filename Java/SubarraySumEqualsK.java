// https://leetcode.com/problems/subarray-sum-equals-k/
// #array #hash-table

/* SIMPLE SOLUTION
class Solution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == k) {
                result++;
            }
            nums[i] += pre;
            pre = nums[i];
        }
        for (int l = 0, r = nums.length - 1; l < r; l++, r--) {
            pre = l > 0 ? nums[l-1] : 0;
            for (int i = l + 1; i <= r; i++) {
                if (nums[i] - pre == k) result++;
                if (i != r && nums[r] - nums[i-1] == k) result++;
            }   
        }
        return result;
    }
}
*/
/*
Co nhieu dai luong
=> co dinh 1 dai luong => giam so luong phan tu bien thien...
=> cung 1 huong.

    // [l, j] = K
    presum[r] - presum[l-1] = K => sum(l, r) = K

    presum[r] - presum[l-1] = K
    presum[r] - K = presum[l-1]
    cnt(presum[i]) = presum[r] - K
*/
class Solution {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        //      value, cnt
        HashMap<Integer, Integer> map = new HashMap<>();
        int presum = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            presum += nums[i];
            if (map.containsKey(presum - k)) {
                result += map.get(presum - k);
            }
            if (map.containsKey(presum)) {
                map.replace(presum, map.get(presum) + 1);
            }
            else {
                map.put(presum, 1);
            }
        }
        return result;
    }
}