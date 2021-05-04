// https://leetcode.com/problems/first-unique-character-in-a-string/
// #hash-table #string
class Solution {
  public int firstUniqChar(String s) {
    int max = s.length();
    int size = 'z' - 'A' + 1;
    int[] source = new int[size];
    Arrays.fill(source, max);
    for (int i = 0; i < s.length(); i++) {
      int idx = s.charAt(i) - 'A';
      if (source[idx] == max) {
        source[idx] = i;
      } else if (source[idx] < max) {
        source[idx] = max + 1;
      }
    }
    int ret = max;
    for (int idx : source) {
      ret = Math.min(ret, idx);
    }

    return (ret == max) ? (-1) : ret;
  }
}
