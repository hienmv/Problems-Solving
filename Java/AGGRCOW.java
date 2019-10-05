/** https://www.spoj.com/problems/AGGRCOW/
 * tag: #binary-search
 * minimum distance between two position in a range. 
 */

import java.util.Scanner;
import java.util.Arrays;

class AGGRCOW {

    static int getResult(int[] source, int countPos) {
        Arrays.sort(source);
        int startVal = 0;
        int endVal = source[source.length - 1] - source[0];
        int ans = 0;
        int midVal;
        
        while (startVal <= endVal) {
            midVal = startVal + (endVal - startVal) / 2;
            int prev = source[0];
            int count = 0;
            for (int i=1; i < source.length; i++) {
                if (source[i] - prev >= midVal) {
                    count++;
                    prev = source[i];
                }
            }
            if (count >= countPos - 1) {
                if (midVal > ans) {
                    ans = midVal;
                }
                startVal = midVal + 1;
            } else {
                endVal = midVal - 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        int n, c;
        for (int t=0; t < testcases; t++) {
            n = sc.nextInt();
            c = sc.nextInt();
            int[] source = new int[n];
            int tmp;
            for (int i=0; i < n; i++) {
                tmp = sc.nextInt();
                source[i] = tmp;
            }
            int result = getResult(source, c);
            System.out.println(result);
        }
    }
}
