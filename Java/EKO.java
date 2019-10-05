/** https://www.spoj.com/problems/EKO/
 * #binary-search
 */
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EKO {
    // true if cut off less than M metres of wood
    public static boolean checkInvalid(int val, long[] sumArr, int[] arr, int m) {
        int left = 0;
        int right = arr.length-1;
        int mid;
        int targetIdx=-1;
        // binarySearch of library
        while (left < right) {
            mid = left + (right - left) / 2;
            if (arr[mid] > val) {
                targetIdx = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (targetIdx == -1) return true;
        long minusVal = targetIdx > 0 ? sumArr[targetIdx-1] : 0; 
        if (sumArr[sumArr.length-1] - minusVal - (sumArr.length-targetIdx) * (long)val >= m) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        MyScanner sc = new MyScanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        long[] sumArr = new long[n];
        int tmp;
        for (int i=0; i < n; i++) {
            tmp = sc.nextInt();
            arr[i] = tmp;
        }
        Arrays.sort(arr);
        sumArr[0] = arr[0];
        for (int i=1; i < n; i++) {
            sumArr[i] = sumArr[i-1] + arr[i];
        }
        // special case
        if (n == 1) {
            System.out.println(arr[0] - m);
            return;
        }
        int left = 1;
        int right = arr[arr.length - 1];
        int midHeight = 0;
        int maxValidHeight = 0;
        boolean checkInvalidFlg = false;
        while (left <= right) {
            midHeight = left + (right - left) / 2; 
            checkInvalidFlg = checkInvalid(midHeight, sumArr, arr, m);
            if (!checkInvalidFlg) {
                maxValidHeight = midHeight;
                left = midHeight + 1;
            } else {
                right = midHeight - 1;
            }
        }
        System.out.println(maxValidHeight);

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