// https://leetcode.com/problems/flood-fill/
// #dfs
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int target = image[sr][sc];
        int m = image.length;
        int n = image[0].length;

        boolean[][] visited = new boolean[m][n];
        Deque<Pixel> queue = new LinkedList<>();
        // start pixel
        visited[sr][sc] = true;
        image[sr][sc] = newColor;
        
        queue.addLast(new Pixel(sr, sc));
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        
        while(!queue.isEmpty()) {
            Pixel p = queue.pollFirst();
            for (int i = 0; i < 4; i++) {
                int x = p.x + dx[i], y = p.y + dy[i];
                if (validPixel(image, x, y, visited) && image[x][y] == target) {
                    visited[x][y] = true;
                    image[x][y] = newColor;
                    queue.addLast(new Pixel(x, y));
                }
            }
        }
        return image;
    }
    private boolean validPixel(int[][] image, int x, int y, boolean[][] visited) {
        if (x == -1 || x == image.length) return false;
        if (y == -1 || y == image[0].length) return false;
        if (visited[x][y]) return false;
        return true;
    }
    private class Pixel {
        int x, y;
        Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}