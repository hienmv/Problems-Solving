/**
 * https://www.hackerearth.com/practice/basic-programming/bit-manipulation/basics-of-bit-manipulation/practice-problems/algorithm/escape-the-night/
 */

import java.util.Scanner;

class EscapeNight {
    static final int MAX = (int)1e9 + 7;
    public static void main(String[] args ) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        for (int t=0; t < testcases; t++) {
            long n = sc.nextLong();
            long sum = 0, val = 0, v = 1;
            // 64bit
            for (int i=1; i < 63; i++) {
                for (int j=0; j < i; j++) {
                    val = (v << i) | (v << j);
                    if (val <= n) {
                        sum += val;
                        sum %= MAX;
                    } else {
                        break;
                    }
                }
            }
            System.out.println(sum);
        }
    }
}