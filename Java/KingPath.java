/** https://codeforces.com/problemset/problem/242/C
 *  idea: use Dijkstra with adjacency matrix
 */

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Point implements Comparable<Point>{
    int x, y;
    Point( int _x, int _y) {
        this.x = _x; 
        this.y = _y;
    }

    public int compareTo(Point other) {
        if (this.x == other.x){
            return this.y - other.y;
        } else {
            return this.x - other.x; 
        }
    }
}

class Node implements Comparable<Node>{
    Point p;
    int dist;
    Node(Point _p, int _dist) {
        this.p = _p;
        this.dist = _dist;
    }
    public int compareTo(Node other) {
        return this.dist - other.dist;
    }
}
public class KingPath {
    static int max_val = (int)1e9 + 1;
    static boolean isValidPoint(HashSet<Point> graph, HashSet<Point>visitedPoint, Point point) {
        if (point.x < 0 || point.x >= max_val) return false;
        if (point.y < 0 || point.y >= max_val) return false;
        if (!graph.contains(point)) return false;
        if (visitedPoint.contains(point)) return false;
        return true;
    }

    static void Dijkstra(HashSet<Point> graph,  Point source, Point destination) {
        HashSet<Point> visitedPoint = new HashSet<>();
        visitedPoint.add(source);
        int[] dx = {-1, -1, -1, 0, 0, 1 ,1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        HashMap<Point, Integer> dist = new HashMap<>();        
        dist.put(source, 0);

        Node startVertex = new Node(source, 0);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(startVertex);

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            Point point = node.p;
            int w = node.dist;
            visitedPoint.add(point);
            for (int i=0; i < dx.length; i++) {
                Point tmp = new Point(point.x + dx[i], point.y + dy[i]);
                if (isValidPoint(graph, visitedPoint, tmp)) {
                    if (dist.containsKey(tmp)) {
                        if ( w + 1 < dist.get(tmp)) {
                            dist.replace(tmp, w+1);
                            pq.add(new Node(tmp, dist.get(tmp)));
                        }
                    } else {
                        dist.put(tmp, w+1);
                        pq.add(new Node(tmp, dist.get(tmp)));
                    }
                }
            }
        }

        if (dist.containsKey(destination)) {
            System.out.println(dist.get(destination));
        } else {
            System.out.println(-1);
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x0 = sc.nextInt();
        int y0 = sc.nextInt();
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int n = sc.nextInt();
        HashSet<Point> graph = new HashSet<>();
        for(int i=0; i < n; i++) {
            int r = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            while (a <= b) {
                graph.add(new Point(r, a++));
            }
        }

        Dijkstra(graph, new Point(x0, y0), new Point(x1, y1));        
    }
}