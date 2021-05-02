// https://leetcode.com/problems/counting-elements/
// #array
class Solution {
    public int countElements(int[] arr) {
        int[] cnt = new int[10001];
        for (int i = 0; i < arr.length; i++) {
            cnt[arr[i]]++;
        }
        int ret = 0;
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] != 0 && cnt[i + 1] != 0) {
                ret += cnt[i];
            }
        }
        return ret;
    }
}