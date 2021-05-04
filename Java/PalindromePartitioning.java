// https://leetcode.com/problems/palindrome-partitioning/
// #dynamic-programming #backtracking #dfs

class Solution {
  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<List<String>>();
    ArrayList<String> curList = new ArrayList<>();
    backtracking(0, result, curList, s);
    return result;
  }

  private void backtracking(int start, List<List<String>> result, List<String> curList, String s) {
    if (start >= s.length()) {
      result.add(new ArrayList<>(curList));
    }
    for (int i = start; i < s.length(); i++) {
      if (isPalindrome(start, i, s)) {
        // forward
        curList.add(s.substring(start, i + 1));

        backtracking(i + 1, result, curList, s);

        // backward
        curList.remove(curList.size() - 1);
      }
    }
  }

  private boolean isPalindrome(int start, int end, String s) {
    while (start < end) {
      if (s.charAt(start) != s.charAt(end)) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }
}
