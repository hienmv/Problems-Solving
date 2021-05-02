// https://leetcode.com/problems/number-complement/
// #bit-manipulation
class Solution {
    public int findComplement(int num) {
        /*
        int ret = 0;
        for (int i = 31; i >= 0; i--) {
            if (1 == ((num >> i) & 1)) {
                for(int j = i - 1; j >= 0; j--) {
                    if(0 == ((num >> j) & 1)) {
                        ret += (1 << j);
                    }
                }
                break;
            }
        }
        return ret;
        */
        
        int j = 0;
        for (int i = 31; i >= 0; i--) {
            if (1 == ((num >> i) & 1)) {
                j = i;
                break;
            }
        }
        int ret = ((1 << (j+1)) - 1) ^ num;
        return ret;
    }
}