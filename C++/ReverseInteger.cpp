// https://leetcode.com/problems/reverse-integer/
// #math
#include<limits.h>
class Solution {
public:
    int reverse(int x) {
        long res = 0;
        
        while (x != 0)
        {
            res *=  10;
            res += x % 10;
            x /= 10;
        }
        return (res < INT_MIN || res > INT_MAX) ? 0 : res;
    }
};