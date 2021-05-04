// https://leetcode.com/problems/roman-to-integer/
// #math #string
class Solution {
  public int romanToInt(String s) {
    int[] arr = new int['Z' - 'A' + 1];
    arr['I' - 'A'] = 1;
    arr['V' - 'A'] = 5;
    arr['X' - 'A'] = 10;
    arr['L' - 'A'] = 50;
    arr['C' - 'A'] = 100;
    arr['D' - 'A'] = 500;
    arr['M' - 'A'] = 1000;
    int number = arr[s.charAt(0) - 'A'];
    for (int i = 1; i < s.length(); i++) {
      number += arr[s.charAt(i) - 'A'];
      if (isExp(s.charAt(i - 1), s.charAt(i))) {
        number -= 2 * arr[s.charAt(i - 1) - 'A'];
      }
    }

    return number;
  }

  private static boolean isExp(char left, char right) {
    if (left == 'I' && (right == 'V' || right == 'X')) {
      return true;
    }
    if (left == 'X' && (right == 'L' || right == 'C')) {
      return true;
    }
    if (left == 'C' && (right == 'D' || right == 'M')) {
      return true;
    }

    return false;
  }
}
