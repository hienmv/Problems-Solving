// https://leetcode.com/problems/number-of-islands/
// #bfs #dfs #dsu
class Solution {
  public int numIslands(char[][] grid) {
    int rows = grid.length;
    if (rows == 0) return 0;
    int cols = grid[0].length;
    boolean[][] visited = new boolean[rows][cols];
    int ret = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == '1' && !visited[i][j]) {
          ret++;
          fill(grid, visited, i, j, rows, cols);
        }
      }
    }
    return ret;
  }

  private static int[] dx = {-1, 0, 0, 1};
  private static int[] dy = {0, -1, 1, 0};

  private static boolean valid(boolean[][] visited, int x, int y, int rows, int cols) {
    if (x < 0 || x >= rows) return false;
    if (y < 0 || y >= cols) return false;
    if (visited[x][y]) return false;
    return true;
  }

  private class Node {
    int x, y;

    Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private void fill(char[][] grid, boolean[][] visited, int row, int col, int rows, int cols) {
    visited[row][col] = true;
    Deque<Node> dq = new LinkedList<>();
    dq.add(new Node(row, col));
    while (!dq.isEmpty()) {
      Node cur = dq.pollFirst();
      for (int i = 0; i < dx.length; i++) {
        int x = cur.x + dx[i];
        int y = cur.y + dy[i];
        if (valid(visited, x, y, rows, cols) && grid[x][y] == '1') {
          visited[x][y] = true;
          dq.addLast(new Node(x, y));
        }
      }
    }
  }
}
