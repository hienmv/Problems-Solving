/**
 * #kmp
 */

import java.util.Scanner;
class GaintAndSifat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = Integer.parseInt(sc.nextLine());
        for(int t=1; t <= testcase; t++) {
            String s = sc.nextLine();
            String p = sc.nextLine();
            // remove space
            s = preProcess(s);
            // build prefix array
            int[] prefix = new int[p.length()];
            calculatePrefix(p, prefix);
            // search
            int result = kmpSearch(s, p, prefix);
            System.out.println("Case " + t + ": " + result);
        }
    }
    public static String preProcess(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    public static int kmpSearch(String s, String p, int[] prefix) {
        int n = s.length();
        int m = p.length();
        int i=0, j=0;
        int result = 0;
        while(i < n) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                result++;
                j = prefix[j - 1];
            }
            else if (i < n && s.charAt(i) != p.charAt(j)) {
                if (j != 0) {
                    j = prefix[j - 1];
                }
                else {
                    i++;
                }
            }
        }
        return result;
    }

    public static void calculatePrefix(String pattern, int[] prefix) {
        prefix[0] = 0;
        int m = pattern.length();
        int j=0, i=1;
        while(i < m) {
            if(pattern.charAt(i) == pattern.charAt(j)) {
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