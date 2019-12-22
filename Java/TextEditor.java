/**
 * #kmp #binary-search
 */

import java.util.Scanner;
class TextEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        String B = sc.nextLine();
        int n = Integer.parseInt(sc.nextLine());

        // calculate prefix
        int[] prefix = new int[B.length()];
        calculatePrefix(B, prefix);
        //
        int right = B.length();
        int left = 0;
        int ret, result = -1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            ret = kmpSearch(A, B, prefix, mid + 1);
            if (ret >= n) {
                result = mid + 1;
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        
        System.out.println((result == -1) ? "IMPOSSIBLE" : B.substring(0, result));
    }
    public static int kmpSearch(String s, String p, int[] prefix, int m) {
        int n = s.length();
        int i=0, j=0;
        int count = 0;
        while (i < n) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                count++;
                j = prefix[j-1];
            }
            else if ( i < n && s.charAt(i) != p.charAt(j)) {
                if (j != 0) {
                    j = prefix[j-1];
                }
                else {
                    i++;
                }
            }
        }
        return count;
    }
    public static void calculatePrefix(String p, int[] prefix) {
        prefix[0] = 0;
        int j=0, i=1;
        while (i < p.length()) {
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