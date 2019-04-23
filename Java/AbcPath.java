/**
    https://www.spoj.com/problems/ABCPATH/
    idea: dfs recursion
 */

import java.util.Scanner;
import java.util.ArrayList;

class AbcPath {
    static boolean isValidVertex(int x, int y, char value, char[][] graph) {
        if(x < 0 || x >= graph.length) return false;
        if(y < 0 || y >= graph[0].length) return false;
        if(graph[x][y] != value) return false;
        return true;
    }
    static int getMaxAbcPath(Point vertex, char[][] graph, boolean[][] visitedArr) {
        int count = 1;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {1, 0, -1, -1, 1, 1, 0, -1};
        for (int i=0; i < dx.length; i++) {
            Point tmp = new Point(vertex.x + dx[i], vertex.y + dy[i]);
            int c = 0;
            if(isValidVertex(tmp.x, tmp.y, (char)(graph[vertex.x][vertex.y] + 1), graph) 
                    && !visitedArr[tmp.x][tmp.y]) {

                visitedArr[tmp.x][tmp.y] = true;
                c += getMaxAbcPath(tmp, graph, visitedArr);
                if (c + 1 > count) {
                    count = 1 + c;
                }
            } else {
                c = 0;
            }
        }

        return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> resultArr = new ArrayList<>();
        int caseCount = 0;
        
        while(true) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            if(h == 0 && w == 0) {
                break;
            }
            
            caseCount++;
            int maxAbcPath = 0;
            boolean[][] visitedArr = new boolean[h][w];
            char[][] graph = new char[h][w];
            for(int i=0; i < h; i++) {
                String str = sc.next();
                graph[i] = str.toCharArray();
            }

           // get max ABC path
            for(int r=0; r < graph.length; r++) {
                for(int c=0; c < graph[0].length; c++) {
                    if(graph[r][c] == 'A') {
                        int tmp = getMaxAbcPath(new Point(r, c), graph, visitedArr);
                        if(tmp > maxAbcPath) {
                            maxAbcPath = tmp;
                        }
                    }
                }
            }
            resultArr.add("Case " + caseCount + ": " + maxAbcPath);
        }
        for(String str : resultArr) {
            System.out.println(str);
        }
    }
}

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}