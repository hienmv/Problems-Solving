/** https://codeforces.com/problemset/problem/385/C
 * #binary-search #implementation
 */
 
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;

import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class BearAndPrimeNumbers {
    private static class Range {
        int left, right;
        Range(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    private static int max = 10000001;
    public static void main(String[] args) {
        MyScanner sc = new MyScanner(System.in);
        int n = sc.nextInt();
        // get input to map
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = sc.nextInt();
            if (!hashMap.containsKey(v)) {
                hashMap.put(v, 1);
            }
            else {
                hashMap.replace(v, hashMap.get(v) + 1);
            }
        }
        int m = sc.nextInt();
        Range[] queries = new Range[m];
        int left, right;
        for (int i = 0; i < m; i++) {
            left = sc.nextInt();
            right = sc.nextInt();
            queries[i] = new Range(left, right);
        }

        // calcualte largetFactor that is prime which each key in arrKey can separate.
        int[] largestFactor = new int[max];
        for (int i = 2; i < max; i++) {
            largestFactor[i] = i;
        }
        for (int i = 2; i < largestFactor.length; i++) {
            if (largestFactor[i] != i) continue;
            for (int k = 2; i * k < largestFactor.length; k++) {
                largestFactor[i * k] = i;
            }
        }
        int[] result = new int[max];
        Set<Integer> set = hashMap.keySet();
        for (int value : set) {
            int cnt = hashMap.get(value);
            while(value > 1) {
                int x = largestFactor[value];
                result[x] += cnt;
                while(value % x == 0) {
                    value /= x;
                }
            }
        }
        for (int i = 2; i < result.length; i++) {
            result[i] += result[i - 1];
        }

        // process
        for (Range range : queries) {
            if ( range.left >= max) {
                System.out.println(0);
            }
            else {
                if (range.right >= max) {
                    range.right = max - 1;
                }
                System.out.println(result[range.right] - result[range.left - 1]);
            }
        }
    }
}

class MyScanner {
    BufferedReader br;
    StringTokenizer st;

    public MyScanner(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }
    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt() {
        return Integer.parseInt(next());
    }
    long nextLong() {
        return Long.parseLong(next());
    }
    double nextDouble() {
        return Double.parseDouble(next());
    }
    String nextLine() {
        String str = "";
        try {
                str = br.readLine();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return str;
    }
}