// https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
// #greedy #sorting
class Solution {
    public int minDeletions(String s) {
        int[] count = new int['z' - 'a' + 1];
        for(char c : s.toCharArray()) {
            count[c - 'a'] += 1;
        }
        Arrays.sort(count);
        System.out.println(Arrays.toString(count));
        HashSet<Integer> set = new HashSet<>(); // used number set
        int pre = 0;
        int result = 0;
        // O(26 * 26)
        for(int i : count) {
            if (i != 0) {
                if (i == pre) {
                    // reduce pre to new pre
                    while (pre != 0 && set.contains(pre)) { // find lower bound of pre -> check to can put one slot [lb i] => recursive.
                        result++;
                        pre--;
                    }
                    
                    // add new pre to used number set
                    set.add(pre);
                } else {
                    set.add(i);
                }
                
                // update current pre
                pre = i;
            }
        }
        return result;
    }
}