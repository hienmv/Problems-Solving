// https://leetcode.com/problems/jewels-and-stones/
// #hash-table
class Solution {
  public int numJewelsInStones(String J, String S) {
    int[] source = new int['z' - 'A' + 1];
    for (char c : S.toCharArray()) {
      source[c - 'A'] += 1;
    }
    int ret = 0;
    for (char c : J.toCharArray()) {
      ret += source[c - 'A'];
    }
    return ret;
  }
}
