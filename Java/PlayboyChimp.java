/** https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=316&page=show_problem&problem=1552
 *  idea: binary search
 */
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class PlayboyChimp {
    public static int upperBound(int[] arr, int val) {
        int left = 0;
        int right = arr.length - 1;
        int ret = -1;
        int mid;
        // upper bound
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] > val) {
                ret = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ret;
    }
    public static int lowerBound(int[] arr, int val) {
        // lower bound
        int left = 0;
        int right = arr.length - 1;
        int ret = -1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] < val) {
                ret = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ret;
    }
    public static String getResult(int[] arr, int val) {
        int ret = upperBound(arr, val);
        String r = (ret == -1) ? "X" : Integer.toString(arr[ret]);

        ret = lowerBound(arr, val);
        String l = (ret == -1) ? "X" : Integer.toString(arr[ret]);

        return (l + " " + r);
    }
    public static void main(String[] args) {
        MyScanner sc = new MyScanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int tmp;
        for (int i = 0; i < n; i++) {
            tmp = sc.nextInt();
            arr[i] = tmp;
        }
        int q = sc.nextInt();

        String result;
        for (int i = 0; i < q; i++) {
            tmp = sc.nextInt();
            result = getResult(arr, tmp);
            System.out.println(result);
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