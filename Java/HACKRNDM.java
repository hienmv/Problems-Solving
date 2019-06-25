/** https://www.spoj.com/problems/OPCPIZZA/
 *  idea: binary search or two pointer
 */

import java.util.*;
import java.io.*;
import java.util.Arrays;

class HACKRNDM {
    public static void main(String[] args) {
        MyScanner sc = new MyScanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        int tmp;
        for(int i=0; i < n; i++) {
            tmp = sc.nextInt();
            arr[i] = tmp;
        }
        Arrays.sort(arr);
        int count = 0;
        /* two pointer
        left = 0
        right = 1

        arr[right] - arr[left]
        < diff: increase right
        > diff: increase left
        = diff: increase left, right
        */
        int left = 0, right = 1;
        while (right < arr.length) {
            if (arr[right] - arr[left] == k) {
                count++;
                right++;
                left++;
            } else if (arr[right] - arr[left] > k) {
                left++;
            } else {
                right++;
            }
        }
        System.out.println(count);
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