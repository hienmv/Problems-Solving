/** http://lightoj.com/volume_showproblem.php?problem=1012
 *  idea: a BFS problems.
 */
import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class GuiltyPrince {
    static boolean isValidPoint(int x, int y, char[][] graph, boolean[][] visitedGraph) {
        if (x < 0 || x > graph.length - 1) return false;
        if (y < 0 || y > graph[0].length - 1) return false;
        if (visitedGraph[x][y]) return false;
        if (graph[x][y] == '#') return false;
        return true;
    }

    static int calReachedPoint(Point startPoint, char[][] graph) {
        int count = 1;
        Deque<Point> queue = new LinkedList<>();
        boolean[][] visitedGraph = new boolean[graph.length][graph[0].length];
        queue.addLast(startPoint);
        visitedGraph[startPoint.x][startPoint.y] = true;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Point point = queue.pollFirst();
            for (int i=0; i < dx.length; i++) {
                if (isValidPoint(point.x + dx[i], point.y + dy[i], graph, visitedGraph)) {
                    count++;
                    visitedGraph[point.x + dx[i]][point.y + dy[i]] = true;
                    queue.addLast(new Point(point.x + dx[i], point.y + dy[i]));
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        String[] result = new String[t];
        for (int i=0; i < t; i++) {
            int w = sc.nextInt();
            int h = sc.nextInt();
            char[][] graph = new char[h][w];
            
            for (int k=0; k < h; k++) {
                String temp = sc.next();
                graph[k] = temp.toCharArray();
            }

            int sx=0, sy=0;
            for (int x=0; x < h; x++) {
                for (int y=0; y < w; y++) {
                    if (graph[x][y] == '@') {
                        sx = x;
                        sy = y;
                        break;
                    }
                }
            }
            Point startPoint = new Point(sx, sy);
            String res = "Case " + (i+1) + ": " + calReachedPoint(startPoint, graph);
            result[i] = res;
        }

        for (String s: result) {
            System.out.println(s);
        }
    }

}