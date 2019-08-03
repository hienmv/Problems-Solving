/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3183
 *  binary search
 */

import java.util.StringTokenizer;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;

public class MonkeyAndBamboo {

    public static boolean validEnergy(int[] arr, int val) {
        for (int i=1; i < arr.length; i++) {
            if ((arr[i] - arr[i-1]) > val ) {
                return false;
            } else if (arr[i] - arr[i-1] == val) {
                val--;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        MyScanner sc = new MyScanner(System.in);
        int testcases = sc.nextInt();
        int n, tmp;
        for (int t=1; t < testcases+1; t++) {
            n = sc.nextInt();
            int[] arr = new int[n+1];
            for (int i=1; i < n+1; i++) {
                tmp = sc.nextInt();
                arr[i] = tmp;
            }

            int left = arr[0]; // all value that is before arr[0] is invalid
            int right = arr[n];
            int mid;
            int ret = arr[0];
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (validEnergy(arr, mid)) {
                    ret = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            
            System.out.println("Case " + t + ": " + ret);
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