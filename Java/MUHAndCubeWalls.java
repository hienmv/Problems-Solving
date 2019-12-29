/**
 * #kmp
idea: convert original arrays to gap arrays.
a:  2  4  5  5  4  3  2  3  2  3  3  2  1
a   0  2  1  0 -1 -1 -1  1  -1  1  0 -1  -1 => remove the first element

b: 3 4 4  3  2
   0 1 0 -1 -1  => remove the first element
 */
import java.util.Scanner;
class MUHAndCubeWalls {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        
        int[] arrA = new int[n];
        for(int i=0; i < n; i++) {
            arrA[i] = sc.nextInt();
        }
        int[] arrB = new int[w];
        for(int i=0; i < w; i++) {
            arrB[i] = sc.nextInt();
        }

        // corner case
        if ( w == 1) {
            System.out.println(n);
            return;
        }
        if ( n == 1) {
            System.out.println(0);
            return;
        }

        // convert to gap arrays
        int[] s = new int[n-1];
        for(int i=1; i < n; i++) {
            s[i-1] = arrA[i] - arrA[i-1];
        }
        int[] p = new int[w-1];
        for(int i=1; i < w; i++) {
            p[i-1] = arrB[i] - arrB[i-1];
        }
        int[] prefix = new int[p.length];
        calculatePrefix(p, prefix);
        int result = kmpSearch(s, p, prefix);
        System.out.println(result);
    }

    public static int kmpSearch(int[] s, int[] p, int[] prefix) {
        int n = s.length;
        int m = p.length;
        int i=0, j=0;
        int result = 0;
        while(i < n) {
            if (s[i] == p[j]) {
                i++;
                j++;
            }
            if (j == m) {
                result++;
                j = prefix[j - 1];
            }
            else if (i < n && s[i] != p[j]) {
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
    public static void calculatePrefix(int[] p, int[] prefix) {
        prefix[0] = 0;
        int j=0, i=1;
        while (i < p.length) {
            if (p[i] == p[j]) {
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