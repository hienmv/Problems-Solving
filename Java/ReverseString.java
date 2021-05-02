// https://leetcode.com/problems/reverse-string/
// #two-pointer #string
class Solution {
    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        char tmp;
        while(l < r) {
            tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }
}