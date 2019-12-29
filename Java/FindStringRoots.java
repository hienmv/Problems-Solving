/**
 * #kmp
                          // a b c a b a b c a b a b c a b
                          // 0 0 0 1 2 1 2 3 4 5 6 7 8 9 10
                // a b c a b a b c a b a b c a b
      // a b c a b a b c a b a b c a b

-> duplicate: dup = len - prefix[len - 1];
-> CHECK len % dup = 0 ? 
 */

import java.util.Scanner;
class FindStringRoots {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String s = sc.next();
            if (s.equals("*")) {
                break;
            }

            // calculate prefix
            int[] prefix = new int[s.length()];
            calculatePrefix(s, prefix);

            int result = 1;
            if (prefix[prefix.length - 1] != 0) {
                int duplicateLen = prefix.length - prefix[prefix.length - 1];
                if (prefix.length % duplicateLen == 0) {
                    result = prefix.length / duplicateLen;
                }
            }
            
            System.out.println(result);
        }
    }
    public static void calculatePrefix(String p, int[] prefix) {
        prefix[0] = 0;
        int i=1, j=0;
        while( i < p.length()) {
            if (p.charAt(i) == p.charAt(j)) {
                j++;
                prefix[i] = j;
                i++;
            }
            else {
                if (j != 0) {
                    j = prefix[j-1];
                } 
                else {
                    prefix[i] = 0;
                    i++;
                }
            }
        }
    }
}