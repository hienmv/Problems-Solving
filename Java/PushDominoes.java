// https://leetcode.com/problems/push-dominoes/
// #two-pointer #dynamic-programming
class Solution {
  /*
  Input: ".L.R...LR..L.."
  Output:"LL.RR.LLRRLL.."

  note: check normal.
  separate range to update
      L...L => LLLLL
      R...R => RRRRR
      L...R => L...R
      R...L => RR.LL
  idea: add two bound to result => refactor: make value as a parameter.
  */
  private static void update(StringBuilder sb, int s, int e, char cStart, char cEnd) {

    if (cStart == cEnd) {
      for (int i = s + 1; i < e; i++) {
        sb.setCharAt(i, cStart);
      }
    } else if (cStart == 'R' && cEnd == 'L') {
      for (int i = s + 1, j = e - 1; i < j; i++, j--) {
        sb.setCharAt(i, 'R');
        sb.setCharAt(j, 'L');
      }
    }
  }

  public String pushDominoes(String dominoes) {
    StringBuilder result = new StringBuilder(dominoes);
    int s = -1;
    char sChar = 'L';
    int i = 0;
    for (i = 0; i < dominoes.length(); i++) {
      if (dominoes.charAt(i) != '.') {
        update(result, s, i, sChar, dominoes.charAt(i));
        s = i;
        sChar = dominoes.charAt(i);
      }
    }
    update(result, s, dominoes.length(), sChar, 'R');
    return result.toString();
  }
}
