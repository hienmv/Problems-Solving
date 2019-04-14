/** https://codeforces.com/problemset/problem/540/C
 *  idea: use BFS (customized)
 */

import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

public class IceCave {
    static boolean isValidPoint(int x, int y, char[][] graph, boolean[][] freezeGraph) {
        if (x < 0 || x >= graph.length) return false;
        if (y < 0 || y >= graph[0].length) return false;
        if (freezeGraph[x][y]) return false;
        return true;
    }
    static boolean canReachDestinantion(Point startPoint, Point targetPoint, char[][] graph) {
        Deque<Point> queue = new LinkedList<>(); 
        boolean[][] freezeGraph = new boolean[graph.length][graph[0].length];
        // re-initilize start Point
        graph[startPoint.x][startPoint.y] = '.';
        queue.addLast(startPoint);
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while(!queue.isEmpty()) {
            Point p = queue.pollFirst();
            if (p.equals(targetPoint) && graph[targetPoint.x][targetPoint.y] == 'X') {
                return true;
            }
            if (!freezeGraph[p.x][p.y]) {
                if (graph[p.x][p.y] == '.') {
                    graph[p.x][p.y] = 'X';
                    for (int i=0; i < dx.length; i++) {
                        if (isValidPoint(p.x + dx[i], p.y + dy[i], graph, freezeGraph)) {
                            queue.add(new Point(p.x + dx[i], p.y + dy[i]));
                        }
                    }
                } else {
                    freezeGraph[p.x][p.y] = true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] graph = new char[n][m];
        for (int x=0; x < graph.length; ++x) {
            String str = sc.next();
            graph[x] = str.toCharArray();
        }
        
        int r1 = sc.nextInt();
        int c1 = sc.nextInt();
        Point startPoint = new Point(r1-1, c1-1);
        int r2 = sc.nextInt();
        int c2 = sc.nextInt();
        Point targetPoint = new Point(r2-1, c2-1);

        if (canReachDestinantion(startPoint, targetPoint, graph)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }
}