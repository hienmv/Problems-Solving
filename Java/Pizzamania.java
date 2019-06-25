/** https://www.spoj.com/problems/OPCPIZZA/
 *  idea: binary search
 */

import java.util.*;
import java.io.*;
import java.util.Arrays;

class Pizzamania {
    public static void main(String[] args) {
        MyScanner sc = new MyScanner(System.in);
        int t = sc.nextInt();
        int n, m;
        for(int i=0; i < t; i++) {
            n = sc.nextInt();
            m = sc.nextInt();
            int[] arr = new int[n];
            int tmp;
            for(int k=0; k < n; k++) {
                tmp = sc.nextInt();
                arr[k] = tmp;
            }
            Arrays.sort(arr);
            int count = 0;
            int left = 0, right = n-1;
            while (left < right) {
                if (arr[left] + arr[right] == m) {
                    count++;
                    right--;
                    left++;
                } else if (arr[left] + arr[right] > m) {
                    right--;
                } else {
                    left++;
                }
            }
            System.out.println(count);
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