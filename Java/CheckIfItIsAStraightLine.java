// https://leetcode.com/problems/check-if-it-is-a-straight-line/
// #array #math #geometry
class Solution {
    // straight line: y = ax + b
    public boolean checkStraightLine(int[][] coordinates) {
        int x1 = coordinates[0][0], y1 = coordinates[0][1];
        int x2 = coordinates[1][0], y2 = coordinates[1][1];
        int c = y1 - y2, d = x1 - x2;
        for (int i = 2; i < coordinates.length; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];  
            if (c * (x - x1) + d * y1 != d * y) return false;
        }
        return true;
    }
}