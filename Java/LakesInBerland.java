/* https://codeforces.com/contest/723/problem/D
*  idea: DFS
*/

import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

public class LakesInBerland {
    
    static boolean isValidLakePoint(int row, int col, char[][] graph, boolean[][] visitedGraph) {
        if (row < 0 || row > graph.length-1) return false;
        if (col < 0 || col > graph[0].length-1) return false;
        if (visitedGraph[row][col]) return false;
        if (graph[row][col]== '*') return false;
        return true;
    }

    static void DFS(Point startPoint, char[][] graph, boolean[][] visitedGraph, HashMap<Point, Integer> lakesResult){
        Deque<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int count = 0;
        while (!queue.isEmpty()) { 
            Point p = queue.pollLast();
            count++;
            visitedGraph[p.x][p.y] = true;
            for (int i=0; i < dx.length; i++) {
                if(isValidLakePoint(p.x + dx[i], p.y + dy[i], graph, visitedGraph)) {
                    queue.addLast(new Point(p.x + dx[i], p.y + dy[i]));
                }
            }
        }
        if (lakesResult != null) {
            lakesResult.put(startPoint, count);
        }
    }

    static void fillLand(Point startPoint, char[][] graph) {
        boolean[][] visitedGraph = new boolean[graph.length][graph[0].length];
        Deque<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) { 
            Point p = queue.pollLast();
            visitedGraph[p.x][p.y] = true;
            graph[p.x][p.y] = '*';
            for (int i=0; i < dx.length; i++) {
                if(isValidLakePoint(p.x + dx[i], p.y + dy[i], graph, visitedGraph)) {
                    queue.addLast(new Point(p.x + dx[i], p.y + dy[i]));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        char[][] graph = new char[n][m];
        for(int row=0; row < n; row++){
            String str = sc.next();
            graph[row] = str.toCharArray();
        }
        
        boolean[][] visitedGraph = new boolean[graph.length][graph[0].length];        
        
        // check the invalid lake
        Deque<Point> invalidLakePointQueue = new LinkedList<>();
        for (int row = 0; row < graph.length; row += graph.length-1) {
            for(int col=0; col < graph[0].length; col++) {
                if(graph[row][col] == '.') {
                    invalidLakePointQueue.addLast(new Point(row, col));
                }
            }
        }
        for (int row=1; row < graph.length-1; row++) {
            for(int col=0; col < graph[0].length; col += graph[0].length-1) {
                if (graph[row][col] == '.') {
                    invalidLakePointQueue.addLast(new Point(row, col));
                }
            }
        }
        
        while(!invalidLakePointQueue.isEmpty()) {
            Point point = invalidLakePointQueue.pollFirst();
            DFS(point, graph, visitedGraph, null);
        }
       
        HashMap<Point, Integer> lakesResult = new HashMap<>();
        for(int row=1; row < graph.length-1; row++) {
            for(int col=1; col < graph[0].length-1; col++) {
                if(isValidLakePoint(row, col, graph, visitedGraph)) {
                    DFS(new Point(row, col), graph, visitedGraph, lakesResult);
                }
            }
        }

        // result
        HashMap<Point, Integer> sortedLakesResult = lakesResult.entrySet().stream().sorted((e1, e2)-> {
            if (e1.getValue() != e2.getValue()) {
                return e1.getValue().compareTo(e2.getValue());
            } else {
                if (e1.getKey().x != e2.getKey().x) {
                    return e1.getKey().x - (e2.getKey().x);
                } else {
                    return e1.getKey().y - (e2.getKey().y);
                }
            }
        }).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        int result = lakesResult.size()-k;
        System.out.println(result);
        if( result > 0) {
            int count=0;
            for(Point p: sortedLakesResult.keySet()) {
                if (count >= result) {
                    break;
                }
                fillLand(p, graph);
                count++;
            }
        }
        
        for(char[] row: graph) {
            for(char c: row){
                System.out.print(c);
            }
            System.out.println("");
        }
    }
}

class Point {
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }
}