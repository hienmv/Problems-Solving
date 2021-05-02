// https://leetcode.com/problems/find-all-anagrams-in-a-string/
// #hash-table
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        // count frequency of all characters inside p
        // for each left => end at current ith => check it whether is an anagram with counting frequency ..
        
        int[] frequencyAnagram = new int['z' - 'a' + 1];
        int[] currentFrequency = new int['z' - 'a' + 1];
        for(char c : p.toCharArray()) {
            frequencyAnagram[c - 'a'] += 1;
        }

        int left = 0;
        int len = p.length();
        ArrayList<Integer> result = new ArrayList();
        for (int i = 0; i < s.length(); i++) {
            currentFrequency[s.charAt(i) - 'a'] += 1;
            if (i - left + 1 == len) {
                if (Arrays.equals(frequencyAnagram, currentFrequency)) {
                    result.add(left); 
                }
                currentFrequency[s.charAt(left) - 'a'] -= 1; 
                left += 1;
            }
        }
        return result;
    }
}