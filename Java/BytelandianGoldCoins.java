/**
 * #dynamic-programming #top-down
 */
import java.util.Scanner;

class BytelandianGoldCoins {

    public static int maxPre = 1_000_000; // 10^9 -> 10^6 : 10 step, and 10^6 can stored in array.

    public static long calculate(long[] maxArr, int n) {
        if (n < 2) {
            maxArr[n] = n;
            return maxArr[n];
        }
        if (n <= maxPre && maxArr[n] != 0) {
            return maxArr[n];
        }
        // divide and conquer
        long a = calculate(maxArr, n/4);
        if (n/4 <= maxPre) {
            maxArr[n/4] = a;
        }
        long b = calculate(maxArr, n/3);
        if (n/3 <= maxPre) {
            maxArr[n/3] = b;
        }
        long c = calculate(maxArr, n/2);
        if (n/2 <= maxPre) {
            maxArr[n/2] = c;
        }
        long val = Math.max(n, a + b + c);
        if (n <= maxPre) {
            maxArr[n] = val;
        }
        return val;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // pre-calculate 
        long[] maxArr = new long[maxPre+1];
        calculate(maxArr, maxPre);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            long val = calculate(maxArr, n);
            System.out.println(val);
        }
    }
}