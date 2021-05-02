// https://leetcode.com/problems/minesweeper/
// #dfs #bfs
class Solution {
    
    public char[][] updateBoard(char[][] board, int[] click) {
        
        Node root = new Node(click[0], click[1]);
        Deque<Node> queue = new LinkedList<>();
        queue.addLast(root);
        
        // corner case
        if (board[root.x][root.y] == 'M') {
            board[root.x][root.y] = 'X';
            return board;
        }
        
        // normal case
        while(!queue.isEmpty()) {
            Node node = queue.pollLast();
            
            // update value at node
            int mineCount = 0;
            for (int i = 0; i < 8; i++) {
                int x = dx[i] + node.x;
                int y = dy[i] + node.y;
                if (isValidSquare(board, x, y) && board[x][y] == 'M') {
                    mineCount++;
                }
            }
            if (mineCount == 0) {
                board[node.x][node.y] = 'B';
            }
            else {
                board[node.x][node.y]  = (char)(mineCount + '0');
                continue;
            }
            
            // add neighbour to the queue
            for (int i = 0; i < 8; i++) {
                int x = dx[i] + node.x;
                int y = dy[i] + node.y;
                if (isValidSquare(board, x, y) && board[x][y] == 'E') {
                    queue.addLast(new Node(x, y));
                }
            }
        }
        return board;
    }
    
    private static boolean isValidSquare(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length) {
            return false;
        }
        if (y < 0 || y >= board[0].length) {
            return false;
        }
        return true;
    }
    private static int[] dx = {-1,  0,  1, -1, 1, -1, 0, 1};
    private static int[] dy = {-1, -1, -1,  0, 0,  1, 1, 1};
    private static class Node {
        int x; int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    };
}