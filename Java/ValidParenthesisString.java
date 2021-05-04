// https://leetcode.com/problems/valid-parenthesis-string/
// #string
class Solution {
  /**
   * count min, max of potential valid '(' | Example:
   * "(())((())()()(*)(*()(())())())()()((()())((()))(*"
   *
   * <p>min: 1 max: 3
   */
  public boolean checkValidString(String s) {
    int min = 0, max = 0;
    for (char ch : s.toCharArray()) {
      if (ch == '(') {
        min += 1;
      } else {
        min -= 1;
      }
      if (ch == ')') {
        max -= 1;
      } else {
        max += 1;
      }

      if (max < 0) {
        return false;
      }

      if (min < 0) {
        min = 0;
      }
    }
    return min == 0;
  }
}
