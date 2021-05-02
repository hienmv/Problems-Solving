/**
 * #dynamic-programming #dijkstra
 * 
1
6 5
3 1 7 4 2                   3   1   7   4   2
2 1 3 1 1                   5   8  10   8   6
1 2 2 1 8                   9  12  12  11  16
2 2 1 5 3
2 1 4 4 4
5 2 7 5 1

chieu cua bai toan -> khong doi. (luon di ve 1 phia). -> khong tao thanh 1 chu trinh (khong di tu do).
-> xac dinh bai toan co so -> ... dau hieu dp.

When he moves from one tile to a tile in the next row,
he can only move to the tile just below it or diagonally to the left or right.

dp[i][j] = tổng tối ưu khi đi từ dòng đầu tiên tới ô (i, j)
           phụ thuộc (i-1, j-1), (i-1, j), (i-1, j+1)

dx = {0, -1, 1}
dy = {-1, -1, -1}

FOR DP, have trick


0 0 0 0 0 0 0
0 3 1 7 4 2 0                 3   1   7   4   2
0 2 1 3 1 1 0                 5   8  10   8   6
0 1 2 2 1 8 0                 9  12  12  11  16
0 2 2 1 5 3 0
0 2 1 4 4 4 0
0 5 2 7 5 1 0

0 .. r-1 => 1 .. r
0 .. c-1 => 1 .. c
===> not need check boundary.
 */

 import java.util.Scanner;
 class PhilosophersStone {
     public static boolean checkValid(int i, int j, int h, int w) {
        if (i < 0 || i >= h) return false;
        if (j < 0 || j >= w) return false;
        return true;
     }
     public static int getPreMaxValue(int[][] matrix, int i, int j, int h, int w) {
        int[] dy = {-1, 0, 1};
        i -= 1;
        int max = 0;
        for (int yy : dy) {
            if (checkValid(i, j + yy, h, w)) {
                max = Math.max(max, matrix[i][j+ yy]);
            }
        }
        return max;
     }
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int testcase = sc.nextInt();
         for (int t=0; t < testcase; t++) {
             int h = sc.nextInt();
             int w = sc.nextInt();
             int[][] matrix = new int[h][w];
             for (int i=0; i < h; i++) {
                 for (int j=0; j < w; j++) {
                    matrix[i][j] = sc.nextInt();
                 }
             }
             for (int i=1; i < h; i++) {
                 for (int j=0; j < w; j++) {
                    matrix[i][j] += getPreMaxValue(matrix, i, j, h, w);
                 }
             }
             int result = 0;
             for (int i=0; i < h; i++) {
                for (int j=0; j < w; j++) {
                   result = Math.max(result, matrix[i][j]);
                }
             }
             System.out.println(result);
         }
     }
 }