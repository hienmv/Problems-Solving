// https://leetcode.com/problems/single-number-iii/
// #bit-manipulation
class Solution {
    public int[] singleNumber(int[] nums) {
        // one: hash set.
        // bit: 3 xor time 
        //     1. xor => remove all the element appearing twice. => xor of result

        // xor_result = first ^ second
        int xor_result = 0;
        for (int num : nums) {
            xor_result ^= num; 
        }
        // a = xor_result & (xor_result - 1)
        // => remove least significant. (1) of xor_result.
        // a ^ xor_result => remaining result in which one bit that is 1. => bit đầu tiên mà first và second khác nhau.
        int setbit = xor_result & (xor_result - 1) ^ xor_result; 

        int first = 0;
        for(int num : nums)
        {
            // tìm ra tất cả các số mà tại bit (setbit) = 1 
            // xor chúng lại => reduce duplicated elements.
            // => first.
            if((num & setbit) > 0)
            {
                
                first ^= num;
            }
        }
        
        int[] result = {first, first ^ xor_result};
        return result;

    }
}