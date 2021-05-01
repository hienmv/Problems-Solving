class PartitionEqualSubsetSum {
    // problem: https://leetcode.com/problems/partition-equal-subset-sum/submissions/
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (len < 2 || sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        
        // can create target from len numbers or not 
        int[][] dp = new int[target + 1][len]; 
        
        //check(nums, dp, target, len - 1);
        checkBottomUp(nums, dp, target, len - 1);
        
        return (dp[target][len - 1] == 1);
    }
    
    // DP top-down O(nlogn)
    // find way to create target in range [0, idx]
    //      case 1: find way to create (target - nums[idx]) in range [0, idx-1]
    //      case 2: find way to create target in range [0, idx-1]
    private int check(int[] nums, int[][] dp, int target, int idx) {
        if (idx < 0 || target < 0) {
            return -1;
        }
        if (target == 0) {
            dp[target][idx] = 1;
        }
        
        if (dp[target][idx] != 0) {
            return dp[target][idx];
        }
        
        if (check(nums, dp, target - nums[idx], idx - 1) == 1) {
            dp[target][idx] = 1;
        } else {
            dp[target][idx] = check(nums, dp, target, idx - 1);
        }
        
        return dp[target][idx];
    }
    
    // bottom-up O(n*m)
    private void checkBottomUp(int[] nums, int[][] dp, int target, int lastIdx) {
        // init: can create 0 from all elements
        for (int i = 0; i <= lastIdx; i++) {
            dp[0][i] = 1;
        }
        
        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= lastIdx; j++) {
                // not use current element or use current element to create target j
                if ((dp[i][j-1] == 1) || (i >= nums[j] && dp[i - nums[j]][j-1] == 1)) {
                    dp[i][j] = 1;
                }
            }
        }

    }
}