// https://leetcode.com/problems/repeated-dna-sequences/
// #hash-table #bit-manipulation
class Solution {
  // normal hash map
  public List<String> findRepeatedDnaSequences(String s) {
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    ArrayList<String> result = new ArrayList<>();
    if (s.length() <= 10) return result;

    for (int i = 0; i <= s.length() - 10; i++) {
      String sub = s.substring(i, i + 10);
      if (map.containsKey(sub)) {
        map.replace(sub, map.get(sub) + 1);
      } else {
        map.put(sub, 1);
      }
    }
    for (String key : map.keySet()) {
      if (map.get(key) > 1) {
        result.add(key);
      }
    }
    return result;
  }
  // other : only need 2 bit to present A C G T

}
