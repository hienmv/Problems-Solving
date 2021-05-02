// https://leetcode.com/problems/ransom-note/
// #string
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int size = 'z' - 'A' + 1;
        int[] source = new int[size];
        for (char c : magazine.toCharArray()) {
            source[c - 'A'] += 1;
        }
        for (char c : ransomNote.toCharArray()) {
            if (source[c - 'A'] == 0) return false;
            source[c - 'A'] -= 1;
        }
        return true;
    }
}