// https://leetcode.com/problems/backspace-string-compare/
// #two-pointer #stack
class Solution {
    /*
    summary


    note
    - 
 
    idea
    - normalize()
    - normalize S and T
    */
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int backspaceS = 0;
        int backspaceT = 0;
        while (i >= 0 || j >= 0) {
            // normalize
            while(i >= 0) {
                if (S.charAt(i) == '#') {
                    backspaceS++;
                }
                else if(backspaceS > 0) {
                    backspaceS--;
                }
                else {
                    break;
                }
                i--;
            }
            // normalize
            while(j >= 0) {
                if (T.charAt(j) == '#') {
                    backspaceT++;
                }
                else if(backspaceT > 0) {
                    backspaceT--;
                }
                else {
                    break;
                }
                j--;
            }
            
            // check
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) {
                return false;
            }
            if ((i >= 0) != (j >= 0)) {
                return false;
            }
            i--;
            j--;
        }
       
        return true;
    }
}