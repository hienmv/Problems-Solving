// https://leetcode.com/problems/remove-k-digits/
// #stack #greedy
class Solution {
    // 1432219 K=3
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (n <= k) return "0";
        if (k == 0) return num;
        
        StringBuilder sb = new StringBuilder(num);
        int count = 0;
        boolean flag = false;
        int i = 1;
        // 1432219 K=3
        /*
              |
            1219
        i=2
        n=4
        sb=1219
        count=3
        
        
        */
        while (i < n) {
            if (sb.charAt(i) < sb.charAt(i-1)) 
            {
                while(count < k && i > 0 && sb.charAt(i) < sb.charAt(i-1)) {
                    sb.deleteCharAt(i-1);
                    count++;
                    n--;
                    i--;
                }
                if (count == k) break;
                if (i == 0) i = 1;
            }
            else {
                i++;
            }
        }
        while (count < k) {
            sb.deleteCharAt(sb.length() - 1);
            count++;
        }
        
        int st = 0;
        for (i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '0') {
                break;
            }
            st = i+1;
        }
        String ret = sb.toString().substring(st, sb.length());
        return ret.equals("") ? "0" : ret;
    }
}