// https://leetcode.com/problems/jump-game/
// #array #greedy
/*
class Solution {
    public boolean canJump(int[] nums) {
        int lastStep = nums.length - 1;
        if (lastStep == 0) return true;
        if (nums[0] >= lastStep) return true;
        
        int i = 0;
        while(i <= lastStep) {
            int maxCurStep = i + nums[i];
            int nextStep = maxCurStep, jumpPos = i;
            for (int j = i+1 ; j <= maxCurStep ; j++) {
                if (j + nums[j] >= nextStep) {
                    nextStep = j + nums[j];
                    jumpPos = j;
                }
            }
            if (nextStep >= lastStep) return true;
            if (i == jumpPos) return false;
            i = jumpPos;
        }
        return false;
    }
}
*/
/* BETTER WAY 

i ----------------------i
    j--------------------------------n
      --------------------------------
                       ---------------
class Solution {
    public boolean canJump(int[] nums) {
        int lastStep = nums.length - 1;
        if (lastStep == 0) return true;
        if (nums[0] >= lastStep) return true;
        
        int i = 0;
        int maxCurStep = i + nums[i];
        while(i <= lastStep) {
            int temp = maxCurStep;
            for (int j = i+1 ; j <= temp ; j++) {
                if (j + nums[j] >= maxCurStep) {
                    maxCurStep = j + nums[j];
                }
            }
            if (maxCurStep >= lastStep) return true;
            if (i == temp) return false;
            i = temp;
        }
        return false;
    }
}
*/

/*
BEST WAY
maxCurStep = 0
for i = 0 .. lastStep:
    if i > maxCurStep:
      -> False
    maxCurStep = max(maxCurStep, i + nums[i])
return maxCurStep >= lastStep
*/
class Solution {
    public boolean canJump(int[] nums) {
        
        int lastStep = nums.length - 1;
        if (lastStep == 0) return true;
        if (nums[0] >= lastStep) return true;
        
        int maxCurStep = 0;
        for (int i = 0; i <= lastStep; i++) {
            if (i > maxCurStep) {
                return false;
            }
            maxCurStep = Math.max(maxCurStep, i + nums[i]);
        }
        
        if (maxCurStep >= lastStep) {
            return true;
        }
        return false;
    }
}