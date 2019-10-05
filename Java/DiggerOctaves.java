/** https://www.spoj.com/problems/UCI2009D/
 * #backtracking #bit-manipulation
 * -> bit manipulation: -> 8x8 -> 64bit.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Collections;

class DiggerOctaves {
    static int maxLen = 8;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static boolean check(long bitSet, int n, int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= n) return false;
        if (((bitSet >> ((x*n) + y)) & 1) == 1) return false;
        return true;
    }

    public static void calculate(long bitSet, int n, int startX, int startY, int len, TreeSet<Long> resultSet) {
        // success
        if (len == 0) {
            resultSet.add(bitSet);
            return;
        }

        // try
        for (int i=0; i < dx.length; i++) {
            int x = startX + dx[i];
            int y = startY + dy[i];
            if (check(bitSet, n, x, y)) {
                int k = (x*n) + y;
                bitSet ^= 1l << k;
                calculate(bitSet, n, x, y, len - 1, resultSet);
                bitSet ^= 1l << k;
            }
        }
    } 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        for (int t=0; t < testcases; t++) {
            int n = sc.nextInt();
            
            String row;
            long bitSet = 0;
            for (int i=0; i < n; i++) {
                row = sc.next();
                for(int j=0; j < row.length(); j++) {
                    if(row.charAt(j) != 'X') {
                        bitSet ^= 1l << (i*n + j);
                    }
                }
            }

            // special case
            if (n < 3) {
                System.out.println(0);
                continue;
            }

            // calculate
            TreeSet<Long> resultSet = new TreeSet<>();
            int len = maxLen;
            int k;
            for (int i=0; i < n; i++) {
                for (int j=0; j < n; j++) {
                    k = (i*n) + j;
                    if (((bitSet >> k) & 1) == 0) {
                        bitSet ^= 1l << k;
                        calculate(bitSet, n, i, j, len - 1, resultSet);
                        bitSet ^= 1l << k;
                    }
                }
            }
            System.out.println(resultSet.size());
        }
    }
}

class Point implements Comparable<Point> {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int compareTo(Point other) {
        if (x != other.x) {
            return x - other.x;
        } else {
            return y - other.y;
        }
    }
}