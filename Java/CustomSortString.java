// https://leetcode.com/problems/custom-sort-string/
// #string
class Solution {
  // https://leetcode.com/problems/custom-sort-string/
  public String customSortString(String S, String T) {
    // count frequency of each character in T
    // iterate each character in S, and build new T.

    int[] countT = new int['z' - 'a' + 1];
    for (char c : T.toCharArray()) {
      countT[c - 'a'] += 1;
    }

    int[] countS = new int['z' - 'a' + 1];
    for (int i = 0; i < S.length(); i++) {
      countS[S.charAt(i) - 'a'] = 1;
    }

    // stringbuffer thread-safe : // stringbuilder - un-thread-safe
    /*
        string: [0..10]; =>
        stringbuffer: => "12345"
            string: "1234567_" => O(1) capacity = 8
                copy string output "12345678____"
            =>
    */
    char[] result = new char[T.length()];
    int idx = 0;
    // O(n) - lengh of T

    // add all character of T existing in S
    for (char c : S.toCharArray()) {
      if (countT[c - 'a'] > 0) {
        int max = countT[c - 'a'];
        for (int i = 0; i < max; i++) {
          result[idx + i] = c;
        }
        idx += max;
      }
    }
    // add all character of T not existing in S
    for (int i = 0; i < countT.length; i++) {
      if (countT[i] > 0 && countS[i] == 0) {
        int max = countT[i];
        for (int j = 0; j < max; j++) {
          result[idx + j] = (char) (i + 'a');
        }
        idx += max;
      }
    }

    return new String(result);
  }
}
