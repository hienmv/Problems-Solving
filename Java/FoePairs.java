/** https://codeforces.com/problemset/problem/652/C
 *  idea: two pointer, iterator from last index to first index
 *  number of pairs created from index i depend on number of pairs created from index i+1
 */

import java.util.StringTokenizer;
import java.io.*;

public class FoePairs {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // faster than scanner.
        try {
            String str = br.readLine();
            int n = Integer.parseInt(str.split("\\s")[0]);
            int m = Integer.parseInt(str.split("\\s")[1]);

            int[] thArr = new int[n+1];
            int[] intervalArr = new int[n+1];
            for (int i=0; i < intervalArr.length; i++) {
                intervalArr[i] = intervalArr.length;
            }

            str = br.readLine();
            int id = 1;
            for (String numStr : str.split("\\s")) {
                int tmp = Integer.parseInt(numStr);
                thArr[tmp] = id;
                id++;
            }
            for (int i=0; i < m; i++) {
                str = br.readLine();
                int l = Integer.parseInt(str.split("\\s")[0]);
                int r = Integer.parseInt(str.split("\\s")[1]);
                if (thArr[l] > thArr[r]) {
                    if (intervalArr[thArr[r]] > thArr[l]) {
                        intervalArr[thArr[r]] = thArr[l];
                    }
                } else {
                    if (intervalArr[thArr[l]] > thArr[r]) {
                        intervalArr[thArr[l]] = thArr[r];
                    }
                }
            }

            long sum = 0;
            int maxRight = intervalArr.length;
            for (int idx = intervalArr.length - 1 ; idx > 0; idx--) {
                maxRight = maxRight > intervalArr[idx] ? intervalArr[idx] : maxRight;
                sum += maxRight - idx;
            }
            System.out.println(sum);

        } catch (Exception e) {

        }
    }
}