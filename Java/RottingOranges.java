// https://leetcode.com/problems/rotting-oranges/
// #bfs
class Solution {
    private static class Coordinate {
        int x;
        int y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static boolean checkValidCoordiate(int x, int y, int[][] grid, boolean[][] visited) {
        if (x < 0 || x >= grid.length) return false;
        if (y < 0 || y >= grid[0].length) return false;
        if (visited[x][y]) return false;
        if (grid[x][y] != 1) return false;
        return true;
    }
    public int orangesRotting(int[][] grid) {
        // creat queue to store rotten oranges
        // each rotten oranges 
        //   => get all the oranges that is going to rotten to store in queue
        //   => update minute count
        // check whether a fresh orange is exists or not
        ArrayList<Coordinate> list = new ArrayList<>();
        int n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // check current orange is rotten or not
                if (grid[i][j] == 2) {
                    visited[i][j] = true;
                    list.add(new Coordinate(i, j));
                }
            }
        }
        Queue<ArrayList<Coordinate>> queue = new LinkedList<>();
        queue.add(list);

        // neighbor coornidate ranges
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        int minuteCount = 0;
        while(!queue.isEmpty()) {
            list = queue.poll();
            boolean countable = false;
            ArrayList<Coordinate> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < 4; j++) {
                    int x = list.get(i).x + dx[j];
                    int y = list.get(i).y + dy[j];
                    if (checkValidCoordiate(x, y, grid, visited)) {
                        visited[x][y] = true;
                        countable = true;
                        newList.add(new Coordinate(x, y));
                    }
                }
            }
            if (countable) {
                minuteCount++;
                queue.add(newList);
            }
        }
                        
        // check
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    return -1;
                }
            }
        }
        return minuteCount;
    }
}