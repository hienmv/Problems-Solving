/** https://www.spoj.com/problems/MAKEMAZE/
 *  idea: set all the valid point in the border.
 *  count > 2, check each couple of them that there are any road between them
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
 }

class MakeMaze {
    static boolean validPoint(int x, int y, boolean[][] visitedGraph, char[][] graph) {
        if (x < 0 || x > graph.length - 1) return false;
        if (y < 0 || y > graph[0].length - 1) return false;
        if (visitedGraph[x][y]) return false;
        if (graph[x][y]== '#') return false;
        return true;
    }
    static boolean checkPoint(Point a, Point b, char[][] graph){
        Deque<Point> queue = new LinkedList<>();
        boolean[][] visitedGraph = new boolean[graph.length][graph[0].length];
        queue.add(a);
        visitedGraph[a.x][a.y] = true;
        while (!queue.isEmpty()) {
            Point t = queue.pollFirst();
            /** duyet ma tran, it khi chuyen ve dang tong quat
             *  tao 2 mang chua gia tri thay doi cua x, y
                tx - 1      ty + 0
                tx + 1      ty + 0
                tx + 0      ty - 1
                tx + 0      ty + 1

                dx = [-1, 1,  0, 0]
                dy = [ 0, 0, -1, 1]
                for (int dir = 0; dir < 4; dir++) {
                    if (validPoint(t.x + dx[dir], t.y + dy[dir], visitedGraph, graph)) {
                        // visit
                    }
                }
             */
            if(validPoint(t.x-1, t.y, visitedGraph, graph)) {
                visitedGraph[t.x-1][t.y] = true;
                if(b.x == t.x-1 && b.y == t.y) {
                    return true;
                }
                queue.add(new Point(t.x-1,t.y));
            }
            if(validPoint(t.x+1, t.y, visitedGraph, graph)) {
                visitedGraph[t.x+1][t.y] = true;
                if(b.x == t.x+1 && b.y == t.y) {
                    return true;
                }
                queue.add(new Point(t.x+1,t.y));
            }
            if(validPoint(t.x, t.y-1, visitedGraph, graph)) {
                visitedGraph[t.x][t.y-1] = true;
                if(b.x == t.x && b.y == t.y-1) {
                    return true;
                }
                queue.add(new Point(t.x,t.y-1));
            }
            if(validPoint(t.x, t.y+1, visitedGraph, graph)) {
                visitedGraph[t.x][t.y+1] = true;
                if(b.x == t.x && b.y == t.y+1) {
                    return true;
                }
                queue.add(new Point(t.x,t.y+1));
            }
        }
        return false;
    }
    static boolean checkValid(ArrayList<Point> list, char[][] graph) {
        if (checkPoint(list.get(0), list.get(1), graph)) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        String[] result = new String[t];
        for (int i=0; i < t; ++i) {
            ArrayList<Point> pointList = new ArrayList<>();

            int m = sc.nextInt();
            int n = sc.nextInt();
            char[][] graph = new char[m][n];
            for (int row = 0; row < m; row++) {
                String line = sc.next();
                graph[row] = line.toCharArray();
            }
            boolean[][] visitedGraph = new boolean[m][n];

            for (int row=0; row < m; row++) {
                if (graph[row][0] == '.' && !visitedGraph[row][0]) {
                    visitedGraph[row][0] = true;
                    pointList.add(new Point(row, 0));
                }
                if (graph[row][n-1] == '.' && !visitedGraph[row][n-1]) {
                    visitedGraph[row][n-1] = true;
                    pointList.add(new Point(row, n-1));
                }
            }
            for (int col=0; col < n; col++) {
                if (graph[0][col] == '.' && !visitedGraph[0][col]) {
                    visitedGraph[0][col] = true;
                    pointList.add(new Point(0, col));
                    
                }
                if (graph[m-1][col] == '.' && !visitedGraph[m-1][col]) {
                    visitedGraph[m-1][col] = true;
                    pointList.add(new Point(m-1, col));
                    
                }
            }
            if (pointList.size() != 2) {
                result[i] = "invalid";
            } else {
                boolean validFlg = checkValid(pointList, graph);
                result[i] = validFlg ? "valid" : "invalid";
            }
        }
        
        for (String s: result) {
            System.out.println(s);
        }
    }
 }