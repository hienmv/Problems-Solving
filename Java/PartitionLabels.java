// https://leetcode.com/problems/partition-labels/
// #two-pointer #greedy
class Solution {
  public List<Integer> partitionLabels(String S) {
    // two pointer => time: O(n), space: 0(1)
    // "ababcbacadefegdehijhklij"
    //  --------|^^^^^^|@@@@@@@@
    ArrayList<Integer> result = new ArrayList<>();

    // store last_index of each character in String S
    int[] last_index = new int['z' - 'a' + 1];
    for (int idx = 0; idx < S.length(); idx++) {
      last_index[S.charAt(idx) - 'a'] = idx;
    }

    // each item in result list is length of range[range_left, range_right]
    int range_left = 0;
    int range_right = 0;
    for (int idx = 0; idx < S.length(); idx++) {
      int arr_idx = S.charAt(idx) - 'a';

      // check need to update range_right or not
      if (last_index[arr_idx] > range_right) {
        range_right = last_index[arr_idx];
      }

      // check end of current range
      if (idx == range_right) {
        result.add(range_right - range_left + 1);
        range_left = range_right + 1;
      }
    }

    return result;
  }
}
