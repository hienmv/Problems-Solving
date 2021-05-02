// https://leetcode.com/problems/group-anagrams/
// #hash-table #string
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) { 
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for (String s: strs) {
            int[] ca = new int[27];
            for (int k = 0; k < s.length(); k++) {
                ca[(int)(s.charAt(k) - 'a' + 1)]++;
            }
            StringBuilder sb = new StringBuilder();
            for (int k = 1; k < ca.length; k++) {
                sb.append(ca[k] + "#");
            }
            String key = sb.toString();
            if (map.containsKey(key)) {
                map.get(key).add(s);
            }
            else {
                List<String> ls = new ArrayList<>();
                ls.add(s);
                map.put(key, ls);
                result.add(ls);
            }
        }
        
        return result;
    }
}