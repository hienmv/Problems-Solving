// https://leetcode.com/problems/perform-string-shifts/
// #array #math
class Solution {
    public String stringShift(String s, int[][] shift) {
        int[] shiftCnt = new int[2];
        for (int[] sh : shift) {
            shiftCnt[sh[0]] += sh[1];
        }
        int len = s.length();
        int cnt = ( len - (shiftCnt[0]  % len) + (shiftCnt[1] % len)) % len;
        if (cnt != 0) {
            String s1 = s.substring(0, len - cnt);
            String s2 = s.substring(len - cnt, len);
            // StringBuilder sb = new StringBuilder(s);
            // for (int i = 0; i < s.length(); i++) {
            //     int idx = (i + cnt) % len;
            //     sb.setCharAt(idx, s.charAt(i));
            // }
            // return sb.toString();
            return (s2 + s1);
        }
        return s;
    }
}