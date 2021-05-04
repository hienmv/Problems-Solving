// https://leetcode.com/problems/longest-valid-parentheses/
// #string #dynamic-programming
class Solution {
  /*
  0  1  2  3  4  5  6  7  8  9  10  11
     )  (  (  (  (  (  (  )  (  )   )

  I.      8.            10.            11
  V.   8-7+1+I[6].  10-9+1+I[8].  11-6+1+I[5]

  Stack: [2,3,4,5]
  */
  public int longestValidParentheses(String s) {
    Deque<Integer> openTag = new LinkedList<>();
    int size = s.length() + 1;
    int[] count = new int[size];
    int result = 0;
    for (int i = 1; i < size; i++) {
      if (s.charAt(i - 1) == ')') {
        if (!openTag.isEmpty()) {
          int idx = openTag.pollLast();
          count[i] = (i - idx + 1) + count[idx - 1];
          if (count[i] > result) {
            result = count[i];
          }
        }
      } else {
        openTag.addLast(i);
      }
    }
    return result;
  }
}
